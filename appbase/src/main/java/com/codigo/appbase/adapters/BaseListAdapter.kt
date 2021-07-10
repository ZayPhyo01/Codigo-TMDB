package com.codigo.appbase.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.appbase.viewholders.BaseViewHolder
import java.util.ArrayList


abstract class BaseListAdapter<T : BaseViewHolder<W>, W>(
    context: Context,
    callback: DiffUtil.ItemCallback<W>
) :
    ListAdapter<W, T>(callback) {

    protected var mLayoutInflater: LayoutInflater

    protected var mData: MutableList<W>? = null

    val items: List<W>
        get() = if (mData == null) ArrayList() else mData!!

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mData = ArrayList()
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        mData?.let {
            holder.setData(it[position])
        }
    }

    fun setNewData(newData: MutableList<W>) {
        submitList(newData)
        mData = newData
    }

    fun appendNewData(newData: List<W>) {
        mData!!.addAll(newData)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): W? {
        return if (position < mData!!.size) mData!![position] else null

    }

    fun removeData(data: W) {
        mData!!.remove(data)
        notifyDataSetChanged()
    }

    fun addNewData(data: W) {
        mData!!.add(data)
        notifyDataSetChanged()
    }

    fun addDataAtPositionZero(data: W) {
        mData!!.add(0, data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData = ArrayList()
        notifyDataSetChanged()
    }
}