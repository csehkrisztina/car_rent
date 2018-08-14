package com.rent.rest_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import com.rent.service_api.DashboardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DashboardControllerImplTest {

    @InjectMocks
    private DashboardControllerImpl dashboardController;

    @Mock
    private DashboardService dashboardService;

    @Test
    public void getLocations_ExpectsListOfLocations() {
        when(dashboardService.getAllLocations()).thenReturn(new ArrayList<>());

        List<LocationDto> result = dashboardController.getLocations();

        List<LocationDto> expected = new ArrayList<>();
        Assert.assertEquals(expected, result);
    }
}
