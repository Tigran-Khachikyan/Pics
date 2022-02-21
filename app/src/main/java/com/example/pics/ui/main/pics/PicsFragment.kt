package com.example.pics.ui.main.pics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.example.pics.adapers.pics.PicsAdapter
import com.example.pics.databinding.FragmentPicsBinding
import com.example.pics.ui.ScreenState
import com.example.pics.utils.visibleOrGone
import org.koin.androidx.viewmodel.ext.android.viewModel

class PicsFragment : Fragment() {

    companion object {
        fun newInstance() = PicsFragment()
    }

    private val viewModel: PicsViewModel by viewModel()
    private var _binding: FragmentPicsBinding? = null
    private val binding get() = _binding!!
    private lateinit var picsAdapter: PicsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observeStateChanges()
        observeEffects()
        viewModel.loadPics()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        with(binding) {
            recyclerPics.run {
                setHasFixedSize(true)
                picsAdapter = PicsAdapter {
                    viewModel.seeMorePics()
                }
                adapter = picsAdapter
            }
        }
    }

    private fun scrollToSecondPic() {
        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        smoothScroller.targetPosition = 1
        binding.recyclerPics.layoutManager?.startSmoothScroll(smoothScroller)
    }

    private fun observeStateChanges() {
        viewModel.state.observe(
            viewLifecycleOwner, { state ->
                with(binding) {
                    loader.visibleOrGone(state.screenState == ScreenState.LOADING)
                    picsAdapter.submitList(state.pics)
                }
            }
        )
    }

    private fun observeEffects() {
        viewModel.effects.observe(
            viewLifecycleOwner, { effect ->
                when (effect) {
                    is PicsEffects.ShowError -> Toast.makeText(
                        requireContext(), effect.error.message, Toast.LENGTH_LONG
                    ).show()
                    is PicsEffects.ScrollToSecondPic -> scrollToSecondPic()
                }
            }
        )
    }
}
