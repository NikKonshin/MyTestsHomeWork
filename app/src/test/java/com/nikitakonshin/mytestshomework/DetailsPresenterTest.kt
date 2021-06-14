package com.nikitakonshin.mytestshomework

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nikitakonshin.mytestshomework.presenter.details.DetailsPresenter
import com.nikitakonshin.mytestshomework.view.details.ViewDetailsContract
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter
    private var _viewContract: ViewDetailsContract? = null

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter()
        presenter.onAttach(viewContract)
        _viewContract = presenter._viewContract!!
    }

    @Test
    fun onIncrement_Test() {
        presenter.onIncrement()
        verify(_viewContract, times(1))?.setCount(anyInt())
    }

    @Test
    fun onDecrement_Test() {
        presenter.onDecrement()
        verify(_viewContract, times(1))?.setCount(anyInt())
    }

    @Test
    fun onAttach_Test() {
        presenter.onAttach(viewContract)
        assertNotNull(presenter._viewContract)
        assertEquals(viewContract,_viewContract)
    }

    @Test
    fun onDetach_Test() {
        presenter.onDetach()
        assertNull(presenter._viewContract)
    }

    @After
    fun close() {
        presenter.onDetach()
    }
}