export default function SetupFormComponent(props, { emit }) {
    const updateValue = submission => {
        let val = submission.target.value

        emit('update:modelValue', val)
    }

    return { updateValue }
}