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
})

