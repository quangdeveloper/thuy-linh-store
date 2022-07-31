import React, { useRef } from "react";
import ReactSelect from "react-select";

export const MultiSelect = props => {
    const valueRef = useRef(props.value);
    valueRef.current = props.value;

    const selectAllOption = {
        value: "-1",
        label: "Tất cả"
    };

    const isSelectAllSelected = () =>
        valueRef.current.length === props.options.length;

    const isOptionSelected = option =>
        valueRef.current.some(({ value }) => value === option.value) ||
        isSelectAllSelected();

    const getOptions = () => [selectAllOption, ...props.options];

    const getValue = () =>
        isSelectAllSelected() ? [selectAllOption] : props.value;

    const onChange = (newValue, actionMeta) => {
        const { action, option, removedValue } = actionMeta;

        if (action === "select-option" && option.value === selectAllOption.value) {
            props.onChange(props.options, actionMeta);
        } else if (
            (action === "deselect-option" &&
                option.value === selectAllOption.value) ||
            (action === "remove-value" &&
                removedValue.value === selectAllOption.value)
        ) {
            props.onChange([], actionMeta);
        } else if (
            actionMeta.action === "deselect-option" &&
            isSelectAllSelected()
        ) {
            props.onChange(
                props.options.filter(({ value }) => value !== option.value),
                actionMeta
            );
        } else {
            props.onChange(newValue || [], actionMeta);
        }
    };

    return (
        <ReactSelect
            placeholder={props.placeholder}
            isOptionSelected={isOptionSelected}
            options={getOptions()}
            value={getValue()}
            onChange={onChange}
            ref = {props.ref}
            hideSelectedOptions={false}
            closeMenuOnSelect={false}
            isMulti
        />
    );
};
