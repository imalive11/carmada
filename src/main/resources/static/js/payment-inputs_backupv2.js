// Set Variables
let selectedOptionPaymentType = null;
let rate = null;

const getElementById = (id) => document.getElementById(id);
const getElementByQuery = (query) => document.querySelector(query);

const setElementValue = (id, value) => {
  getElementById(id).value = value;
};

const setElementReadOnly = (id, readOnly) => {
  getElementById(id).readOnly = readOnly;
};

//Payment Type Inputs

const updateRate = () => {
  var selectedVehicle = $('#vehicle').val();
  rate = selectedVehicle ? $('#vehicleList [value="' + selectedVehicle + '"]').data('value') : 0;
};

const setPaymentTypeHandler = () => {
  setPaymentType();
  updateRate();
};

const setPaymentType = () => {
  const paymentTypeInput = document.querySelector('input[name="paymentType"]:checked');
  selectedOptionPaymentType = paymentTypeInput ? paymentTypeInput.value : 'FULL_BOUNDARY';

  if (selectedOptionPaymentType === "FULL_BOUNDARY") {
    setElementReadOnly('boundaryShort', true);
    setElementReadOnly('boundary', true);
    setElementValue('boundaryShort', 0);
  } else if (selectedOptionPaymentType === "SHORT") {
    setElementReadOnly('boundary', false);
    setElementReadOnly('boundaryShort', false);
  } else if (selectedOptionPaymentType === "PARTIAL_BOUNDARY") {
    setElementReadOnly('boundaryShort', false);
    setElementReadOnly('boundary', false);
  } else if (selectedOptionPaymentType === "NO_BOUNDARY") {
    setElementReadOnly('boundaryShort', true);
    setElementReadOnly('boundary', true);
    setElementValue('boundaryShort', 0);
    setElementValue('boundary', 0);
  } else if (selectedOptionPaymentType === "CHARGE_BOUNDARY") {
    setElementReadOnly('boundaryShort', false);
    setElementReadOnly('boundary', true);
    setElementValue('boundary', 0);
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

const adjustBoundaryRateHandler = () => {
  if (rate === null || selectedOptionPaymentType === "NO_BOUNDARY" || selectedOptionPaymentType === "CHARGE_BOUNDARY") {
    setElementValue("boundary", 0);
  } else {
    setElementValue("boundary", rate);
  }
};

const adjustBoundaryRate = () => {
  selectedOptionPaymentType = getElementByQuery('input[name="paymentType"]:checked').value;
  setElementValue("boundary", rate);
  const selectedRate = getElementByQuery('input[name="boundaryRate"]:checked').value;

  if ((selectedRate === "weekend" || selectedRate === "holiday") && rate !== null) {
    setElementValue("boundary", rate - 100);
  } else if (selectedRate === "coding" && rate !== null) {
    setElementValue("boundary", rate - 200);
  }

  if (rate === null || selectedOptionPaymentType === "NO_BOUNDARY" || selectedOptionPaymentType === "CHARGE_BOUNDARY") {
    setElementValue("boundary", 0);
  }
};
// End of Payment Type inputs

const otherPaymentsHandler = () => {
  const selectElementFund = getElementByQuery('#otherPaymentsFund');
  const selectElementLoan = getElementByQuery('#otherPaymentsLoan');
  const selectElementContribution = getElementByQuery('#otherPaymentsContribution');
  const selectElementCarwash = getElementByQuery('#otherPaymentsCarwash');
  const selectElementPenalty = getElementByQuery('#otherPaymentsPenalty');

  setElementValue("boundaryFund", selectElementFund.checked ? 50 : 0);
  setElementValue("boundaryLoan", selectElementLoan.checked ? 50 : 0);
  setElementValue("contribution", selectElementContribution.checked ? 50 : 0);
  setElementValue("carwash", selectElementCarwash.checked ? 65 : 0);

  setElementReadOnly('boundaryFund', !selectElementFund.checked);
  setElementReadOnly('boundaryLoan', !selectElementLoan.checked);
  setElementReadOnly('contribution', !selectElementContribution.checked);
  setElementReadOnly('carwash', !selectElementCarwash.checked);
  setElementReadOnly('boundary', !selectElementPenalty.checked);
};


const appendPaymentDescriptionHandler = () => {
  const checkboxes = ['extraDriver', 'noDriver', 'absent', 'mainAndRepair', 'accident', 'late'];
  const descriptionField = getElementById("paymentDescription");
  descriptionField.value = "";

  checkboxes.forEach((checkbox) => {
    const element = getElementById(checkbox);
    if (element.checked) {
      descriptionField.value += " " + element.value;
    }
  });
};

const clearPaymentDescriptionHandler = () => {
  const checkboxes = ['extraDriver', 'noDriver', 'absent', 'mainAndRepair', 'accident', 'late'];
  const descriptionField = getElementById("paymentDescription");
  checkboxes.forEach((checkbox) => {
    getElementById(checkbox).checked = false;
  });
  descriptionField.value = "";
};

const validatePaymentDescriptionHandler = () => {
  const myInputValue = getElementById("paymentDescription").value;

  setPaymentType();
  if ((selectedOptionPaymentType === "PARTIAL_BOUNDARY" || selectedOptionPaymentType === "NO_BOUNDARY") && myInputValue === "") {
    getElementById("errorDiv").style.display = "block";
    return false;
  }

  return true;
};



$(document).ready(function() {
  $("#travelDate").val(new Date().toISOString().slice(0, 10));
  $("#paymentDate").val(new Date().toISOString().slice(0, 10));

  const searchParams = new URLSearchParams(window.location.search);
  const paramValue = searchParams.get("paymentId");
  if (paramValue === null) {
      setPaymentTypeHandler();
      getBoundaryRateHandler();
  }
  //Display Driver Name
  $('#drivers').on('change', function() {
    var selectedOption = $('#driverList [value="' + $(this).val() + '"]');
    var selectedDriverTextBox = $('#selectedDriver');
    var fullName = selectedOption.text();
    selectedDriverTextBox.val(fullName);
  });

  //Display Vehicle Name
  $('#vehicle').on('change', function() {
    var selectedOption = $('#vehicleList [value="' + $(this).val() + '"]');
    var selectedVehicleTextBox = $('#selectedVehicle');
    var vehicle = selectedOption.text();
    selectedVehicleTextBox.val(vehicle);
  });

});
