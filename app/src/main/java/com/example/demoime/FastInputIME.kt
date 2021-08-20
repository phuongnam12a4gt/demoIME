package com.example.demoime

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodSubtype
import android.widget.Button


class FastInputIME : InputMethodService(), KeyboardView.OnKeyboardActionListener,
    View.OnClickListener {
    override fun onCreate() {
        super.onCreate()
        Log.i("TAG", "onCreate")
    }

    override fun onCreateInputView(): View {
//        val keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as KeyboardView
//        val keyboard = Keyboard(this, R.xml.number_bad)
//        keyboardView.keyboard = keyboard
//        keyboardView.setOnKeyboardActionListener(this)
//        return keyboardView
        Log.i("TAG", "onCreateInputView")
        val keyboard = Keyboard(this, R.xml.number_bad)
        return layoutInflater.inflate(R.layout.keyboard_view, null).apply {
            if (this is KeyboardView) {
                setOnKeyboardActionListener(this@FastInputIME)
                this.keyboard = keyboard
            }
        }
    }

    override fun onCreateCandidatesView(): View? {
        return null
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        Log.i("TAG", "onStartInputView")
    }

    override fun onCurrentInputMethodSubtypeChanged(newSubtype: InputMethodSubtype?) {
        super.onCurrentInputMethodSubtypeChanged(newSubtype)
    }

    override fun onFinishInput() {
        super.onFinishInput()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDetroy")
    }

    override fun onPress(primaryCode: Int) {
        Log.i("TAG", "onPress")
    }

    override fun onRelease(primaryCode: Int) {
        Log.i("TAG", "onRelease")
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val ic = currentInputConnection ?: return
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> {
                val selectedText = ic.getSelectedText(0)
                if (TextUtils.isEmpty(selectedText)) {
                    // no selection, so delete previous character
                    ic.deleteSurroundingText(1, 0)
                } else {
                    // delete the selection
                    ic.commitText("", 1)
                }
            }
            else -> {
                val code = primaryCode.toChar()
                ic.commitText(code.toString(), 1)
            }
        }
    }

    override fun onText(text: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun swipeLeft() {
        TODO("Not yet implemented")
    }

    override fun swipeRight() {
        TODO("Not yet implemented")
    }

    override fun swipeDown() {
        TODO("Not yet implemented")
    }

    override fun swipeUp() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}