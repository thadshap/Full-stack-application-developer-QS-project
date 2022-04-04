import store from '@/store'
describe('store', () => {
  test('setting userID', async() => {
    const userID = 1;
    await store.commit('SET_USERID', userID)
    const received = store.state.userId;
    expect(received).toStrictEqual(userID)
  })
  test('setting courseID', async() => {
    const courseID = 1;
    await store.commit('SET_COURSEID', courseID)
    const received = store.state.userId;
    expect(received).toStrictEqual(courseID)
  })
  test('setting course', async() => {
    const course = {courseId : 2, courseName : "Statistikk", courseCode : "ISTT1001"};
    await store.commit('SET_COURSE', course)
    const received = store.state.course;
    expect(received).toStrictEqual(course)
  })
})

