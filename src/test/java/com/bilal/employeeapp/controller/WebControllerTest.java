package com.bilal.employeeapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.springframework.ui.Model;

public class WebControllerTest {

    @Test
    public void testIndexPage() {
        
        WebController webController = new WebController();        
        Model model = mock(Model.class);

        String viewName = webController.indexPage();
        
        assertEquals("index", viewName);

        verify(model, times(0)).addAttribute(anyString(), any());
    }

    @Test
    public void testCreateEmployeeForm() {
        
        WebController webController = new WebController();

        String viewName = webController.createEmployeeForm();

        assertEquals("employeeCreate", viewName);
    }
}
	
	







