package ysuzuki.githubclient

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import ysuzuki.githubclient.view.SearchViewModel

/**
 * Created by Yasuhiro Suzuki on 2017/05/20.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(SearchViewModel::class)
class SearchViewModelTest {

    lateinit var mockViewModel: SearchViewModel

    @Before
    fun setUp() {
        mockViewModel = PowerMockito.mock(SearchViewModel::class.java)
        Mockito.`when`(mockViewModel.qualifiers).thenReturn("Android")
    }

    @Test
    @Throws(Exception::class)
    fun getQualifiers_test() {
        assertEquals(mockViewModel.qualifiers, "Android")
    }

}