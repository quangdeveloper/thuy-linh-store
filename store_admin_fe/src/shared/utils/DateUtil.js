//format str:  2021-08-25T17:36:11.157+00:00;
const convertStringToDate = (str_date) => {

    const year = str_date.substring(0, 4);
    const month = str_date.substring(5, 7);
    const day = str_date.substring(8, 10);

    return new Date(year, month, day, 0, 0, 0)
}

//format str:  2021-08-25T17:36:11.157+00:00; =>   2021-08-25
const formatStringDate = (str_date) => {

    return str_date.substring(0, 10)
}


export {
    convertStringToDate,
    formatStringDate
}
