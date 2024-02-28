package com.hh.testapponlineshop.views.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hh.testapponlineshop.R
import com.hh.testapponlineshop.databinding.FragmentRegistrationBinding
import com.hh.testapponlineshop.viewModels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.FormattedTextChangeListener
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class RegistrationFragment : Fragment()
{
    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding!!

    private val viewModel by viewModel<RegistrationViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.nameEditText.doAfterTextChanged { _ ->
            if (isCyrillicOnly(viewModel.name) || viewModel.name.isEmpty())
            {
                binding.nameLayout.error = ""
            }
            else
            {
                binding.nameLayout.error = "Для ввода допустима только кириллица"
            }
            //Не сокращаю в одну строчку для читаемости
            if (isCyrillicOnly(viewModel.name) && isCyrillicOnly(viewModel.surname) && binding.phoneNumberEditText.text.toString().length == 16)
            {
                binding.enterButton.isEnabled = true
            }
            else
            {
                binding.enterButton.isEnabled = false
            }
        }

        binding.surnameEditText.doAfterTextChanged { _ ->
            if (isCyrillicOnly(viewModel.surname) || viewModel.surname.isEmpty())
            {
                binding.surnameLayout.error = ""
            }
            else
            {
                binding.surnameLayout.error = "Для ввода допустима только кириллица"
            }

            if (isCyrillicOnly(viewModel.surname) && isCyrillicOnly(viewModel.name) && binding.phoneNumberEditText.text.toString().length == 16)
            {
                binding.enterButton.isEnabled = true
            }
            else
            {
                binding.enterButton.isEnabled = false
            }
        }

        val slots = UnderscoreDigitSlotsParser().parseSlots("+7 ___ ___-__-__")
        val mask: MaskImpl = MaskImpl.createTerminated(slots)
        mask.isForbidInputWhenFilled = true
        mask.isHideHardcodedHead = true
        mask.placeholder = '*'
        mask.isShowingEmptySlots = true
        val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)

        formatWatcher.setCallback(object : FormattedTextChangeListener
        {
            override fun beforeFormatting(p0: String?, p1: String?): Boolean
            {
                return false
            }

            override fun onTextFormatted(watcher: FormatWatcher?, phoneNumber: String?)
            {
                if (phoneNumber?.length == 16 && isCyrillicOnly(viewModel.surname) && isCyrillicOnly(viewModel.name))
                {
                    binding.enterButton.isEnabled = true
                }
                else
                {
                    binding.enterButton.isEnabled = false
                }
            }

        })
        formatWatcher.installOn(binding.phoneNumberEditText)

        viewModel.isUserNew.observe(viewLifecycleOwner) {
            it?.let {
                if (it)
                {
                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainContentFragment(R.id.catalogFragment))
                }
                else
                {
                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainContentFragment(R.id.mainFragment))
                }
                viewModel.onEnterClicked()
            }
        }

        return binding.root
    }

    private fun isCyrillicOnly(text: String): Boolean
    {
        return text.matches("[а-яА-Я]+".toRegex())
    }
}