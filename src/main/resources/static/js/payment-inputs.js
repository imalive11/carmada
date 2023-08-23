// Set Variables
let selectedOptionPaymentType = null;
let rate = null;

const setElementValue = (id, value) => {
  $("#" + id).val(value);
};

const setElementReadOnly = (id, readOnly) => {
  $("#" + id).prop("readOnly", readOnly);
};

// Payment Type Inputs

const updateRate = () => {
  const selectedVehicle = $("#vehicle").val();
  rate = selectedVehicle ? $("#vehicleList [value='" + selectedVehicle + "']").data("value") : 0;
};

const setPaymentTypeHandler = () => {
  setPaymentType();
  updateRate();
};

const setPaymentType = () => {
  const paymentTypeInput = $('input[name="paymentType"]:checked');
  selectedOptionPaymentType = paymentTypeInput.length ? paymentTypeInput.val() : "FULL_BOUNDARY";

  if (selectedOptionPaymentType === "FULL_BOUNDARY") {
    setElementReadOnly("boundaryShort", true);
    setElementReadOnly("boundary", true);
    setElementValue("boundaryShort", 0);
    setElementValue("boundary", rate);
  } else if (selectedOptionPaymentType === "SHORT") {
    setElementReadOnly("boundary", false);
    setElementReadOnly("boundaryShort", false);
  } else if (selectedOptionPaymentType === "PARTIAL_BOUNDARY") {
    setElementReadOnly("boundaryShort", false);
    setElementReadOnly("boundary", false);
  } else if (selectedOptionPaymentType === "NO_BOUNDARY") {
    setElementReadOnly("boundaryShort", true);
    setElementReadOnly("boundary", true);
    setElementValue("boundaryShort", 0);
    setElementValue("boundary", 0);
  } else if (selectedOptionPaymentType === "CHARGE_BOUNDARY") {
    setElementReadOnly("boundaryShort", false);
    setElementReadOnly("boundary", true);
    setElementValue("boundary", 0);
  }
};

const getBoundaryRate = () => {
  if (rate === null || selectedOptionPaymentType === "NO_BOUNDARY" || selectedOptionPaymentType === "CHARGE_BOUNDARY") {
    return 0;
  } else {
    return rate;
  }
};

const getBoundaryRateHandler = () => {
  updateRate();
  setElementValue("boundary", getBoundaryRate());
};

// const adjustBoundaryRateHandler = () => {
//   if (rate === null || selectedOptionPaymentType === "NO_BOUNDARY" || selectedOptionPaymentType === "CHARGE_BOUNDARY") {
//     setElementValue("boundary", 0);
//   } else {
//     setElementValue("boundary", rate);
//   }
// };

const adjustBoundaryRate = () => {
  selectedOptionPaymentType = $('input[name="paymentType"]:checked').val();
  setElementValue("boundary", rate);
  setElementValue("amountRateBoundary", rate); 
  const selectedRate = $('input[name="rateBoundary"]:checked').val();

  if ((selectedRate === "WEEKEND" || selectedRate === "HOLIDAY") && rate !== null) {
    setElementValue("boundary", rate - 100);
    setElementValue("amountRateBoundary", rate - 100); 

  } else if (selectedRate === "CODING" && rate !== null) {
    setElementValue("boundary", rate - 200);
    setElementValue("amountRateBoundary", rate - 100); 
  }

  if (rate === null || selectedOptionPaymentType === "NO_BOUNDARY" || selectedOptionPaymentType === "CHARGE_BOUNDARY") {
    setElementValue("boundary", 0);
  }
};

// End of Payment Type inputs

const otherPaymentsHandler = () => {
  const selectElementFund = $("#otherPaymentsFund");
  const selectElementLoan = $("#otherPaymentsLoan");
  const selectElementContribution = $("#otherPaymentsContribution");
  const selectElementCarwash = $("#otherPaymentsCarwash");
  const selectElementPenalty = $("#otherPaymentsPenalty");

  setElementValue("boundaryFund", selectElementFund.prop("checked") ? 50 : 0);
  setElementValue("boundaryLoan", selectElementLoan.prop("checked") ? 50 : 0);
  setElementValue("contribution", selectElementContribution.prop("checked") ? 50 : 0);
  setElementValue("carwash", selectElementCarwash.prop("checked") ? 65 : 0);

  setElementReadOnly("boundaryFund", !selectElementFund.prop("checked"));
  setElementReadOnly("boundaryLoan", !selectElementLoan.prop("checked"));
  setElementReadOnly("contribution", !selectElementContribution.prop("checked"));
  setElementReadOnly("carwash", !selectElementCarwash.prop("checked"));
  setElementReadOnly("boundary", !selectElementPenalty.prop("checked"));
};

const appendPaymentDescriptionHandler = () => {
  const checkboxes = ["extraDriver", "noDriver", "absent", "mainAndRepair", "accident", "late"];
  const descriptionField = $("#paymentDescription");
  descriptionField.val("");

  checkboxes.forEach((checkbox) => {
    const element = $("#" + checkbox);
    if (element.prop("checked")) {
      descriptionField.val(descriptionField.val() + " " + element.val());
    }
  });
};

const clearPaymentDescriptionHandler = () => {
  const checkboxes = ["extraDriver", "noDriver", "absent", "mainAndRepair", "accident", "late"];
  const descriptionField = $("#paymentDescription");
  checkboxes.forEach((checkbox) => {
    $("#" + checkbox).prop("checked", false);
  });
  descriptionField.val("");
};

const validatePaymentDescriptionHandler = () => {
  const myInputValue = $("#paymentDescription").val();

  setPaymentType();
  if ((selectedOptionPaymentType === "PARTIAL_BOUNDARY" || selectedOptionPaymentType === "NO_BOUNDARY") && myInputValue === "") {
    $("#errorDiv").css("display", "block");
    return false;
  }

  return true;
};

const updateSelected = () => {
  // Update selected driver
  const selectedDriverOption = $("#driverList [value='" + $("#drivers").val() + "']");
  const selectedDriverTextBox = $("#selectedDriver");
  const driverFullName = selectedDriverOption.text();
  selectedDriverTextBox.val(driverFullName);

  // Update selected vehicle
  const selectedVehicleOption = $("#vehicleList [value='" + $("#vehicle").val() + "']");
  const selectedVehicleTextBox = $("#selectedVehicle");
  const vehicle = selectedVehicleOption.text();
  selectedVehicleTextBox.val(vehicle);
}

$(document).ready(function () {

  const searchParams = new URLSearchParams(window.location.search);
  const paramValue = searchParams.get("paymentId");
  if (paramValue !== null) {
    setPaymentTypeHandler();
    getBoundaryRateHandler();
    updateSelected()
  } else {
    $("#travelDate").val(new Date().toISOString().slice(0, 10));
    $("#paymentDate").val(new Date().toISOString().slice(0, 10));
  }

  // Display Driver Name
  $("#drivers").on("change", function () {
    updateSelected()
  });

  // Display Vehicle
  $("#vehicle").on("change", function () {
    updateSelected()
  });
});
