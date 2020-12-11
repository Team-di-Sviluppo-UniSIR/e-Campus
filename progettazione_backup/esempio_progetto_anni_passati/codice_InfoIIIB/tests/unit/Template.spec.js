import { shallowMount, mount } from '@vue/test-utils'
import Planner from '@/assets/PlannerTS.vue'
import Vue from 'vue'
Vue.config.silent = true;


describe('Template Test', () => { // Suite for testing first iteration

	/**
	 * This test will check if the starting point input is properly accepeted
	 */
	test('Starting point is correctly received', () => {
		const wrapper = shallowMount(Planner);
		const setAddressMock = jest.fn();
		wrapper.setMethods({ setAddress: setAddressMock});
		wrapper.find('#startingPoint').text('Bergamo');
		expect(setAddressMock).toHaveBeenCalled; // Check id the SetAddress function has been called
		wrapper.destroy();
	});


	/**
	 * This test will check if the arrival point input is properly accepeted
	 */
	test('Arrival point is correctly received', () => {
		const wrapper = shallowMount(Planner);
		const setAddressMock = jest.fn();
		wrapper.setMethods({ setAddress: setAddressMock});
		wrapper.find('#arrivalPoint').text('Roma');
		expect(setAddressMock).toHaveBeenCalled; // Check id the SetAddress function has been called
		wrapper.destroy();
	});


	/**
 	 * This test will check if the main() method will be called when the search
 	 * button has been clicked.
 	 */
	test('Main function is called by the Search button', () => {
		const wrapper = shallowMount(Planner);
		const mainMock = jest.fn();
		wrapper.setMethods({ main: mainMock }); 
		wrapper.find('#button').trigger('click'); // click the button
		expect(mainMock).toHaveBeenCalled();
		wrapper.destroy();
	});

		/**
 	 * This test will check if the reset function is called by the Reset button
 	 */
	 test('Reset function is called by the Reset button', () => {
		const wrapper = shallowMount(Planner); // initialize the test
		const resetMock = jest.fn();
		wrapper.setMethods({ reset: resetMock});
		wrapper.find('#reset').trigger('click'); // click the button
		expect(resetMock).toHaveBeenCalled();
		wrapper.destroy();
	});


});

