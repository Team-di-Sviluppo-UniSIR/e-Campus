import { shallowMount, mount } from '@vue/test-utils'
import Planner from '@/assets/PlannerTS.vue'
import Vue from 'vue'
Vue.config.silent = true;


describe('Map Visualizer Test', () => { // Suite for testing third iteration

    // parametro input: array di 2 valori: il primo è bool, il secondo è un array che contiene o le coordinate dei chargers o un array vuoto

    test('displayResultsWithNoCharger', () => {
		const wrapper = shallowMount(Planner);
        const displayResultsMock = jest.fn(x => false);
        wrapper.setMethods({ displayResults: displayResultsMock });
        expect(displayResultsMock([false, []])).toBe(false);
        wrapper.destroy(); 
    });
    
    test('displayResultsWithNoChargerNeeded', () => {
		const wrapper = shallowMount(Planner);
        const displayResultsMock = jest.fn(x => true);
        wrapper.setMethods({ displayResults: displayResultsMock });
        expect(displayResultsMock([true, []])).toBe(true);
        wrapper.destroy();
    });
    
    test('displayResultsWithCharger', () => {
		const wrapper = shallowMount(Planner);
        const displayResultsMock = jest.fn(x => true);
        wrapper.setMethods({ displayResults: displayResultsMock });
        expect(displayResultsMock([true, [(1,2),(3,4)]])).toBe(true);
        wrapper.destroy();
    });


});