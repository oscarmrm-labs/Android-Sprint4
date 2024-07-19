package com.qualentum.sprint4.presentation.add_contacts

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.qualentum.sprint4.R
import com.qualentum.sprint4.databinding.DialogColorPickerBinding

private const val BUNDLE_COLOR_KEY = "backgroundColor"
private const val COLOR_KEY_IDENTIFIER = "colorKeyIdentifier"

class ColorPickerDialog: DialogFragment() {
    private lateinit var binding: DialogColorPickerBinding
    private var color = -16777216

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            color = it.getInt(BUNDLE_COLOR_KEY)
        }
    }
    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogColorPickerBinding.inflate(inflater, container, false)
        inflateBinding()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun inflateBinding() {
        splitUpARGB(color)
        setProgressToSeekBars()
        changeViewBackgroundColor(color)
        setUpSeekBars()
        setOnClickToButtons()
    }

    private fun splitUpARGB(color: Int) {
        binding.apply {
            tvRGBNumberTransparent.text = (Color.alpha(color) / 255 * 100).toString()
            tvRGBNumberRed.text = Color.red(color).toString()
            tvRGBNumberGreen.text = Color.green(color).toString()
            tvRGBNumberBlue.text = Color.blue(color).toString()
        }
    }

    private fun setProgressToSeekBars() {
        binding.apply {
            sbRed.progress = tvRGBNumberRed.text.toString().toInt()
            sbGreen.progress = tvRGBNumberGreen.text.toString().toInt()
            sbBlue.progress = tvRGBNumberBlue.text.toString().toInt()
            sbTransparent.progress = tvRGBNumberTransparent.text.toString().toInt()
        }
    }

    private fun changeViewBackgroundColor(argb: Int) {
        binding.apply {
            colorSelected.setBackgroundColor(argb)
        }
    }

    private fun setUpSeekBars() {
        binding.apply {
            seekBarListeners(sbRed)
            seekBarListeners(sbGreen)
            seekBarListeners(sbBlue)
            seekBarListeners(sbTransparent)
        }
    }

    private fun seekBarListeners(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.apply {
                    when (seekBar?.id) {
                        sbRed.id -> tvRGBNumberRed.text = progress.toString()
                        sbGreen.id -> tvRGBNumberGreen.text = progress.toString()
                        sbBlue.id -> tvRGBNumberBlue.text = progress.toString()
                        sbTransparent.id -> tvRGBNumberTransparent.text = progress.toString()
                    }
                }
                changeViewBackgroundColor(getARGB())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?)  = Unit
        })
    }

    private fun setOnClickToButtons() {
        binding.apply {
            btnCancel.setOnClickListener {
                Toast.makeText(requireActivity(),
                    getString(R.string.dialog_color_picker_toast_dont_save_color), Toast.LENGTH_SHORT).show()
                dismiss()
            }
            btnOk.setOnClickListener {
                Toast.makeText(requireActivity(),
                    getString(R.string.dialog_color_picker_toast_save_color), Toast.LENGTH_SHORT).show()
                val result = Bundle().apply {
                    putInt(BUNDLE_COLOR_KEY, getARGB())
                }
                parentFragmentManager.setFragmentResult(COLOR_KEY_IDENTIFIER, result)
                dismiss()
            }
        }
    }

    private fun getARGB(): Int {
        val red = binding.tvRGBNumberRed.text.toString().toInt()
        val green = binding.tvRGBNumberGreen.text.toString().toInt()
        val blue = binding.tvRGBNumberBlue.text.toString().toInt()
        var alpha = binding.tvRGBNumberTransparent.text.toString().toInt()
        alpha = (alpha / 100.0 * 255).toInt()
        return Color.argb(alpha, red, green, blue)
    }
}