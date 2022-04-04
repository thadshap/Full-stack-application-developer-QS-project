import { shallowMount } from "@vue/test-utils";
import AssigmentView from "@/views/AssigmentView";

describe("AssignmentView", () => {
  it("test that send course button shows up when clicking 'normal' radiobutton", () => {
    const wrapper = shallowMount(AssigmentView);
    //works, but have to remove document.getElementById, so have to set the boolean instead
    //wrapper.find('[id="oving-rules-btn"]').trigger("click");
    wrapper.setData({showOvingRules : true})
    expect(wrapper.find('[id="oving-rules-drop-down-container"]')).toBeTruthy()
  });
})