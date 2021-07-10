package com.codigo.appbase

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.codigo.appbase.adapters.BaseRecyclerAdapter


import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import io.reactivex.Observable
import java.lang.Exception

import java.text.SimpleDateFormat
import java.util.*


fun View.setVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

//Extension Function for showing image
fun ImageView.show(imageUrl: String, @DrawableRes placeHolderImageRes: Int) {
    Glide.with(this)
        .load(imageUrl)
        .apply(
            RequestOptions.centerCropTransform().placeholder(placeHolderImageRes).error(
                placeHolderImageRes
            )
        )
        .into(this)
}

//Extension Function for showing image
fun ImageView.show(imageUrl: String, placeHolderImageRes: Drawable) {
    Glide.with(this)
        .load(imageUrl)
        .apply(
            RequestOptions.fitCenterTransform().placeholder(placeHolderImageRes).error(
                placeHolderImageRes
            )
        )
        .into(this)
}


//Extension Function for showing image
fun ImageView.show(@DrawableRes imageRes: Int) {
    Glide.with(this)
        .load(imageRes)
        .apply(RequestOptions.centerInsideTransform())
        .into(this)
}


//Extension Function for showing image
fun ImageView.network(imageUrl: String) {
    //todo add placeHolder and error replacement photos
    Glide.with(this)
        .load(imageUrl)
        .apply(RequestOptions.centerCropTransform())

        .into(this)
}

//Extension Function for showing image
fun ImageView.show(imageUri: Uri) {
    //todo add placeHolder and error replacement photos
    Glide.with(this)
        .load(imageUri)
        .apply(RequestOptions.centerCropTransform())
        .into(this)
}


fun Array<TextInputLayout>.removeFoci() {
    this.forEach {
        it.clearFocus()
    }
}

fun View.setEnable(enabled: Boolean) {
    this.isEnabled = enabled
}

fun Array<TextInputLayout>.enable(enabled: Boolean) {
    this.forEach {
        it.isEnabled = enabled
    }
}


fun Array<out View>.setVisible(isVisible: Boolean) {

    val visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }

    this.forEach {
        it.visibility = visibility
    }
}


fun ViewGroup.inflater(): LayoutInflater {
    return LayoutInflater.from(this.context)
}

fun inflate(context: Context, parent: ViewGroup, @LayoutRes layoutID: Int) =
    LayoutInflater.from(context).inflate(layoutID, parent, false)

fun ComponentActivity.showShortToast(message: CharSequence) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun ComponentActivity.showShortToast(@StringRes resId: Int) {
    Toast.makeText(applicationContext, resId, Toast.LENGTH_SHORT).show()
}

fun ComponentActivity.showLongToast(message: CharSequence) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
}

fun ComponentActivity.showLongToast(@StringRes resId: Int) {
    Toast.makeText(applicationContext, resId, Toast.LENGTH_LONG).show()
}

fun Fragment.showShortToast(message: CharSequence) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showShortToast(@StringRes resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLongToast(message: CharSequence) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showLongToast(@StringRes resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
}


@SuppressLint("ClickableViewAccessibility")
fun TextView.addPrimaryButtonTextEffext(): TextView {
    this.setOnTouchListener { v, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                this.setPadding(this.paddingLeft, 0, this.paddingRight, 0)
                this.isSelected = true
                this.performClick()
            }
            MotionEvent.ACTION_CANCEL -> {
                this.setPadding(this.paddingLeft, 0, this.paddingRight, 5)
                this.isSelected = false
            }
            MotionEvent.ACTION_UP -> {
                this.setPadding(this.paddingLeft, 0, this.paddingRight, 5)
                this.isSelected = false
            }
        }
        true
    }
    return this
}

fun getRandom(min: Double, max: Double): Double {
    return Math.random() * (max + 1 - min) + min
}


fun getDefaultDateFormatter() = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

infix fun <T> List<T>.nonintersect(
    other: List<T>
): Set<T> {
    val set = this.toHashSet()
    set.removeAll(other)
    return set
}

inline fun <reified T> T.itemToTypeDefinedArray(): Array<T> {
    return arrayOf(this)
}


inline fun <reified T> T.toTypeDefinedList(): List<T> {
    return arrayOf(this).toList()
}


/**
 * for building and adding fragment list to fragmentContainer
 * */


fun RecyclerView.configure(context: Context, adapter: BaseRecyclerAdapter<*, *>) {
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context)
}


fun RecyclerView.configureWithHorizontal(context: Context, adapter: BaseRecyclerAdapter<*, *>) {
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}


fun RecyclerView.configureWithReverseLayout(context: Context, adapter: BaseRecyclerAdapter<*, *>) {
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true).apply {
        stackFromEnd = true
    }
}


fun TextView.changeTextAppearance(context: Context?, resId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        this.setTextAppearance(context, resId)
    } else {
        this.setTextAppearance(resId)
    }
}

@SuppressLint("SimpleDateFormat")
fun getTime(): String {
    return SimpleDateFormat("HH:mm a").format(Calendar.getInstance().time)
}

fun getDp(context: Context, sizeInDp: Int): Int {
    return (sizeInDp * context.resources.displayMetrics.density + 0.5f).toInt()
}

fun <E> SparseArray<E>.getKeyByValue(value: E): Int {
    return this.keyAt(this.indexOfValue(value))
}

fun Toolbar.addBackNavButton(activity: AppCompatActivity, @DrawableRes backIcon: Int) {
    activity.setSupportActionBar(this)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    activity.supportActionBar?.title = ""
    this.navigationIcon = ContextCompat.getDrawable(activity, backIcon)
    this.setNavigationOnClickListener {
        //What to do on back clicked
        activity.onBackPressed()
    }
}

fun BottomSheetDialog.enableExitOnTouchOutside(enabled: Boolean) {
    this.setCancelable(enabled)
    this.setCanceledOnTouchOutside(enabled)
}

fun String.shorten(): String {
    return if (this.length > 20)
        "${this.substring(0, 20)}...${
            this.substring(
                this.length - 10,
                this.length
            )
        } [truncated for ${this.length} chars]"
    else
        this
}

fun String.parseNumber(): Int {
    return try {
        this.toInt()
    } catch (nfe: NumberFormatException) {
        0
    }
}

fun getColorWrapper(context: Context, id: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getColor(id)
    } else {
        context.resources.getColor(id)
    }
}


fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun <T> String.fromJson(@NonNull type: Class<T>): T {
    return Gson().fromJson(this, type)
}

fun String.withKM() = "$this Km"

fun Int.isFirst(): Boolean = this == 0

fun <T> Observable<T>.either(load: () -> T, error: () -> T): Observable<T> {
    return try {
       val data =  load.invoke()
        map {
            data
        }

    } catch (e: Exception) {
        onErrorReturn {
            error.invoke()
        }
    }
}