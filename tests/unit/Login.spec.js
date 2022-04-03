import { shallowMount } from "@vue/test-utils";
import ShowAllStudentsInCourse from "@/views/ShowAllStudentsInCourse";

describe('ShowAllStudents', () => {
  it('test that file input that are read gets processed correctly',async() =>{
    const wrapper = shallowMount(ShowAllStudentsInCourse)

    const lastStudentInTestFile = {email : " karo@gmail.com", firstname : " Karo", lastname : "Sund"}

    await wrapper.setData({ fileInput : "svinsås, eirin, eirin@gmail.com\r\nSund, Karo, karo@gmail.com"})
    await wrapper.setData({ showSendFile: true });

    await wrapper.find('[id="sendFile"]').trigger("click");

    expect(wrapper.vm.students.pop()).toStrictEqual(lastStudentInTestFile)
  })
})
