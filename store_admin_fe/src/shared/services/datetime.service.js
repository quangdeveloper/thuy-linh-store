

const format_date_to_dd_mm_yyyy_from_yyyy_mm_dd = ( date) => {

    return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
}

const format_date_to_YYYY_MM_DD_from_yyyy_mm_dd = (date) => {
    return date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);


}


export {
    format_date_to_dd_mm_yyyy_from_yyyy_mm_dd,
    format_date_to_YYYY_MM_DD_from_yyyy_mm_dd
}