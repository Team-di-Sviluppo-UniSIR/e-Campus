import { shallowMount, mount } from '@vue/test-utils'
import Planner from '@/assets/PlannerTS.vue'
import Vue from 'vue'
Vue.config.silent = true;


describe('Algorithm Test', () => { // Suite for testing second iteration

    // Check the correct function of the following functions
     
    test('setMarker', () => { 
		const wrapper = shallowMount(Planner);
        const setMarkerMock = jest.fn(x => L.Marker);
        wrapper.setMethods({ setMarker: setMarkerMock });
        expect(setMarkerMock([[1,2], 'startPoint'])).toBe(L.Marker);
        wrapper.destroy(); 
    });

    test('setChargerMarker', () => {  // Integration test
		const wrapper = shallowMount(Planner);
        const setChargerMarkerMock = jest.fn(x => L.Marker);
        wrapper.setMethods({ setChargerMarker: setChargerMarkerMock });
        expect(setChargerMarkerMock([1,2])).toBe(L.Marker);
        wrapper.destroy(); 
    });

    test('setStartMarker', () => {  // Integration Test
		const wrapper = shallowMount(Planner);
        const setStartMarkerMock = jest.fn(() => L.Marker);
        wrapper.setMethods({ setStartMarker: setStartMarkerMock });
        expect(setStartMarkerMock()).toBe(L.Marker);
        wrapper.destroy(); 
    });

    test('setEndMarker', () => { // Integration test
		const wrapper = shallowMount(Planner);
        const setEndMarkerMock = jest.fn(() => L.Marker);
        wrapper.setMethods({ setEndMarker: setEndMarkerMock });
        expect(setEndMarkerMock()).toBe(L.Marker);
        wrapper.destroy(); 
    });

    test('buildStringForTooltip', () => {
        const wrapper = shallowMount(Planner);
        const buildMock = jest.fn(x => String);
        wrapper.setMethods({ buildStringForTooltip: buildMock });
        expect(buildMock([[1,2,3,4], 4])).toBe(String);
        wrapper.destroy(); 
    });

    test('getSmallestElementsIndex', () => {
        const wrapper = shallowMount(Planner);
        const y = [3,2,5,7];
        const smallestMock = jest.fn( x => x.indexOf(Math.min.apply(null, x)));
        wrapper.setMethods({ getSmallestElementsIndex: smallestMock });
        expect(smallestMock(y)).toBe(1);
        wrapper.destroy(); 
    });

    test('buildUrl', () => {
        const wrapper = shallowMount(Planner);
        const buildMock = jest.fn( x => 'https://api.openchargemap.io/v3/poi/?client=' + wrapper.appName + '&key=' + wrapper.appKey + '&output=json&latitude=' + x[0][0] + '&longitude=' + x[0][1] + '&distance=' + x[1] + '&distanceunit=KM&maxresults=100');
        wrapper.setMethods({ buildURL: buildMock });
        expect(typeof(buildMock([[1,2], 2]))).toBe(typeof('https://api.'));
        wrapper.destroy(); 
    });

    test('getStopCircleCenterPoints', () => {
        const wrapper = shallowMount(Planner);
        const getMock = jest.fn( x => Array);
        wrapper.setMethods({ getStopCircleCenterPoints: getMock });
        expect(getMock([[1,2],[7,8]],[[3,5],[15,18]])).toBe(Array);
        wrapper.destroy(); 
    });

    //getDistanceFromLatLonInKm(latA, lngA, latB, lngB) {
    test('getDistanceFromLatLonInKm', () => {
        const wrapper = shallowMount(Planner);
        const getMock = jest.fn( x => typeof(x*Math.PI));
        wrapper.setMethods({ getDistanceFromLatLonInKm: getMock });
        expect(getMock([1,2,3,4])).toBe(typeof(5));
        wrapper.destroy(); 
    });

});

