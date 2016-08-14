package com.tellh.androidlibraryarchitecturedemo.dagger2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by tlh on 2016/8/10 :)
 */
public class MvpPresenterTest {
    MvpPresenter mvpPresenter;

    @Mock
    MvpPresenter.UserManager userManager;

    @Before
    public void setUp() throws Exception {
        mvpPresenter = new MvpPresenter(null);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
//        MvpPresenter.UserManager userManager = mock(MvpPresenter.UserManager.class);
        mvpPresenter.setmUserManager(userManager);
        mvpPresenter.login("tlh", "12366666666");
//        verify(userManager).performLogin(anyString(), anyString());
        verify(userManager).performLogin(matches("tellh"), anyString());
    }

    @Test
    public void testMoreThanOneReturnValue() {
        ArrayList arrayList = mock(ArrayList.class);
//        doCallRealMethod().when(arrayList).get(0);
//        when(arrayList.get(0)).thenReturn(123);
//        doReturn(123).when(arrayList).get(0);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(arrayList).get(captor.capture());
        captor.getValue();
//        captor.getAllValues();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数
                Object[] arguments = invocation.getArguments();
                //这里利用参数添加自定义逻辑
                return null;//返回自定义的值
            }
        }).when(arrayList).get(0);
//        doNothing().when(arrayList).get(0);
        //assert
        assertEquals(123, arrayList.get(0));
    }
}