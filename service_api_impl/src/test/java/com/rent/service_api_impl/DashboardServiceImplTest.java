package com.rent.service_api_impl;

import com.rent.model.Brand;
import com.rent.model.FuelType;
import com.rent.model.TransmissionType;
import com.rent.model.dto.LocationDto;
import com.rent.model.entity.LocationEntity;
import com.rent.model.repository.LocationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DashboardServiceImplTest {

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @Mock
    private LocationRepository locationRepository;

    private LocationEntity location;

    @Before
    public void SetUp() {
        location = new LocationEntity();
    }

    @Test
    public void getAllLocations_ExpectsListOfLocationDto() {
        List<LocationEntity> locations = new ArrayList<>();
        locations.add(location);
        when(locationRepository.findAll()).thenReturn(locations);

        List<LocationDto> result = dashboardService.getAllLocations();

        List<LocationDto> expected = new ArrayList<>();
        expected.add(location.toDto());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getAllBrands_ExpectsListOfBrands() {

        List<Brand> result = dashboardService.getAllBrands();

        List<Brand> expected = Arrays.asList(Brand.values());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getAllFuelTypes_ExpectsListOfFuelType() {

        List<FuelType> result = dashboardService.getAllFuelTypes();

        List<FuelType> expected = Arrays.asList(FuelType.values());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getAllTransmissionTypes_ExpectsListOfTransmissionType() {

        List<TransmissionType> result = dashboardService.getAllTransmissionTypes();

        List<TransmissionType> expected = Arrays.asList(TransmissionType.values());
        Assert.assertEquals(expected, result);
    }
}
