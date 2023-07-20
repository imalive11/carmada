$(document).ready(function () {
  dateRangeHandler();
});
const getParameterByName = (name) => {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(name);
};

// Example usage:
const parameterValue = getParameterByName("dateRange");

const dateRangeHandler = () => {
  var selectedRange = $("input[name='dateRange']:checked").val();
  var selectedYear = $("#selected-year").val();
  var selectedStartMonth = $("#from-select").val();
  var selectedEndMonth = $("#to-select").val();

  if (
    selectedRange === "allyear" ||
    selectedRange === "firsthalf" ||
    selectedRange === "secondhalf"
  ) {
    $("#from-select").prop("disabled", true);
    $("#to-select").prop("disabled", true);
    var startDate;
    var endDate;

    if (selectedRange === "allyear") {
      startDate = `${selectedYear}-01-01`;
      endDate = `${selectedYear}-12-31`;
    } else if (selectedRange === "firsthalf") {
      startDate = `${selectedYear}-01-01`;
      endDate = `${selectedYear}-06-30`;
    } else if (selectedRange === "secondhalf") {
      startDate = `${selectedYear}-07-01`;
      endDate = `${selectedYear}-12-31`;
    }
  } else if (selectedRange === "custom") {
    $("#from-select").prop("disabled", false);
    $("#to-select").prop("disabled", false);
    var startDate = `${selectedYear}-${selectedStartMonth}`;
    var endDate = `${selectedYear}-${selectedEndMonth}`;
  }
  $("#startDateInput").val(startDate);
  $("#endDateInput").val(endDate);
};