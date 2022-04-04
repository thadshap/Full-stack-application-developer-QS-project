import { shallowMount } from "@vue/test-utils";
import AddNewCourse from "@/views/AddNewCourse";

describe("AddNewCourse", () => {
  it("test that send course button shows up when clicking 'normal' radiobutton", () => {
    const wrapper = shallowMount(AddNewCourse);
    wrapper.find('[id="normal"]').trigger("click");
    expect(wrapper.find('[id="send"]')).toBeTruthy()
  });
  it("test that number of undergoups fields shows up when clicking 'undergoups' radiobutton", () => {
    const wrapper = shallowMount(AddNewCourse);
    wrapper.find('[id="undergroups"]').trigger("click");
    expect(wrapper.find('[id="invisibleChoices"]')).toBeTruthy()
  });
  it("test that correct amount of input fields get created when entering amount of undergroups.", () => {
    const wrapper = shallowMount(AddNewCourse);
    wrapper.find('[id="undergroups"]').trigger("click");
    wrapper.setData({showGroupDetails : true})
    wrapper.find('[id="NumOfUnderGroups"]').setValue(2);
    wrapper.find('[id="NumOfUnderGroups"]').trigger("input");
    //7 because there are 5 existing before we add 2
    expect(wrapper.findAll('[class="inputFields"]').length).toEqual(7)
  });
})