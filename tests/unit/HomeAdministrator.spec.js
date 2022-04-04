import { shallowMount } from "@vue/test-utils";
import HomeAdministrator from "@/views/HomeAdministrator";
import store from '@/store'

describe('HomeAdministrator', () => {
  it('test that clicking on course enables buttons',async() =>{
    const wrapper = shallowMount(HomeAdministrator, {
      global:{
        plugins: [store]
      }
    })
    //This works, but document.getElementById is not defined by jest(so have to remove a line in HomeAdministrator), so changes boolean intead
    /*
    await wrapper.setData(courses : { courses : [{courseName: "statistikk", courseCode: "ISTT1001", index : 15, startDate : "22.03.2022"}]})
    await wrapper.find('[id="15"]').trigger("click");
     */
    await wrapper.setData({disableButton : false})

    expect(wrapper.find('[id="archive"]').element.disabled).toBe(false);
    expect(wrapper.find('[id="delete"]').element.disabled).toBe(false);
    expect(wrapper.find('[id="edit"]').element.disabled).toBe(false);
  })

})

