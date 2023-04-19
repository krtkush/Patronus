package com.krtkush.patronus.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.krtkush.patronus.navigation.NavigationUtil
import javax.inject.Inject

typealias FragmentBindingInflater<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

open class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: FragmentBindingInflater<VB>
) : Fragment() {

    private var _binding: VB? = null

    val binding: VB get() = _binding!!

    @Inject
    lateinit var navigationUtil: NavigationUtil

    @Inject
    lateinit var baseFragmentLifecycleObserver: BaseFragmentLifeCycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(baseFragmentLifecycleObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = bindingInflater.invoke(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()

        lifecycle.removeObserver(baseFragmentLifecycleObserver)
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }
}