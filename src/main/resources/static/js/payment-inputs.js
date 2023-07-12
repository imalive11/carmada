// Set Variables
let selectedOptionPaymentType = null;
let rate = null;

const getElementById = (id) => document.getElementById(id);
const getElementByQuery = (query) => document.querySelector(query);
const getElementByQueryAll = (query) => document.querySelectorAll(query);

const updateRate = () => {
  const selectElementVehicle = getElementByQuery('#vehicle');
  rate = selectElementVehicle.options[selectElementVehicle.selectedIndex].getAttribute('data-value');
};

const setElementValue = (id, value) => {
  getElementById(id).value = value;
};

const setElementReadOnly = (id, readOnly) => {
  getElementById(id).readOnly = readOnly;
};

const setElementDisabled = (id, disabled) => {
  getElementById(id).disabled = disabled;
};

const setPaymentTypeHandler = () => {
  updateRate();
  setPaymentType();
  setElementValue("boundary", getBoundaryRate());
  clearPaymentDescriptionHandler();
};

const setPaymentType = () => {
  const paymentTypeInput = document.querySelector('input[name="paymentType"]:checked');
  selectedOptionPaymentType = paymentTypeInput ? paymentTypeInput.value : 'fullBoundary';

  const disableElements = ['otherPaymentsFund', 'otherPaymentsLoan', 'otherPaymentsContribution'];
  const enableElements = ['extraDriver', 'noDriver', 'absent', 'mainAndRepair', 'accident'];
  const paymentValues = ['boundaryShort', 'boundary', 'boundaryFund', 'boundaryLoan', 'contribution'];
  const paymentStates = ['boundaryShort', 'boundary'];
  const paymentCheckboxStates = ['otherPaymentsFund', 'otherPaymentsLoan', 'otherPaymentsContribution'];
  const disableRadioButtons = ['extraDriver', 'daily'];
  const enableRadioButtons = ['noDriver', 'absent', 'mainAndRepair', 'accident'];

  disableElements.forEach((element) => setElementDisabled(element, false));
  enableElements.forEach((element) => setElementDisabled(element, true));

  setElementReadOnly('boundaryShort', true);
  setElementReadOnly('boundary', true);
  setElementValue('boundaryShort', 0);

  if (selectedOptionPaymentType === "fullBoundary") {
    disableElements.forEach((element) => setElementDisabled(element, false));
    enableElements.forEach((element) => setElementDisabled(element, false));
    setElementReadOnly('boundaryShort', true);
    setElementReadOnly('boundary', true);
    setElementValue('boundaryShort', 0);
  } else if (selectedOptionPaymentType === "short") {
    disableElements.forEach((elementId) => {
      const element = document.getElementById(elementId);
      element.disabled = true;
      element.checked = false;
    });
    paymentValues.forEach((element) => setElementValue(element, 0));
    setElementValue('boundaryShort', 0);
    setElementDisabled('otherPaymentsFund', true);
    setElementDisabled('otherPaymentsLoan', true);
    setElementDisabled('otherPaymentsContribution', true);
    setElementDisabled('daily', false);
    setElementDisabled('extraDriver', true);
    setElementDisabled('noDriver', true);
    setElementDisabled('absent', true);
    setElementDisabled('mainAndRepair', true);
    setElementDisabled('accident', true);
    setElementReadOnly('boundary', true);
    setElementReadOnly('boundaryShort', false);
  } else if (selectedOptionPaymentType === "daily") {
    disableElements.forEach((element) => setElementDisabled(element, false));
    enableElements.forEach((element) => setElementDisabled(element, true));
    setElementReadOnly('boundaryShort', true);
    setElementReadOnly('boundary', true);
    setElementValue('boundaryShort', 0);
  } else if (selectedOptionPaymentType === "partialBoundary") {
    disableElements.forEach((element) => setElementDisabled(element, false));
    enableElements.forEach((element) => setElementDisabled(element, false));
    setElementReadOnly('boundaryShort', false);
    setElementReadOnly('boundary', false);
    setElementValue('boundaryShort', 0);
  } else if (selectedOptionPaymentType === "noBoundary") {
    paymentValues.forEach((element) => setElementValue(element, 0));
    paymentStates.forEach((element) => setElementReadOnly(element, true));
    paymentCheckboxStates.forEach((element) => setElementDisabled(element, true));
    disableRadioButtons.forEach((element) => setElementDisabled(element, true));
    enableRadioButtons.forEach((element) => setElementDisabled(element, false));
  }
};

const getBoundaryRate = () => {
  if (rate === null || selectedOptionPaymentType === "noBoundary") {
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
  if (rate === null || selectedOptionPaymentType === "noBoundary") {
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

  if (rate === null || selectedOptionPaymentType === "noBoundary") {
    setElementValue("boundary", 0);
  }
};

const otherPaymentsHandler = () => {
  const selectElementFund = getElementByQuery('#otherPaymentsFund');
  const selectElementLoan = getElementByQuery('#otherPaymentsLoan');
  const selectElementContribution = getElementByQuery('#otherPaymentsContribution');
  const selectElementCarwash = getElementByQuery('#otherPaymentsCarwash');

  setElementValue("boundaryFund", selectElementFund.checked ? 50 : 0);
  setElementValue("boundaryLoan", selectElementLoan.checked ? 50 : 0);
  setElementValue("contribution", selectElementContribution.checked ? 40 : 0);
  setElementValue("carwash", selectElementCarwash.checked ? 65 : 0);

  setElementReadOnly('boundaryFund', !selectElementFund.checked);
  setElementReadOnly('boundaryLoan', !selectElementLoan.checked);
  setElementReadOnly('contribution', !selectElementContribution.checked);
  setElementReadOnly('carwash', !selectElementCarwash.checked);
};

const clearOtherPaymentsCheckboxHandler = () => {
  const checkboxes = ['otherPaymentsFund', 'otherPaymentsLoan', 'otherPaymentsContribution', 'otherPaymentsCarwash'];
  const fields = ['contribution', 'boundaryLoan', 'boundaryFund', 'carwash'];

  checkboxes.forEach((checkbox) => {
    getElementById(checkbox).checked = false;
  });

  fields.forEach((field) => {
    setElementValue(field, 0);
    setElementReadOnly(field, true);
  });
};

const appendPaymentDescriptionHandler = () => {
  const checkboxes = ['extraDriver', 'noDriver', 'absent', 'mainAndRepair', 'accident', 'daily'];
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
  const checkboxes = ['extraDriver', 'noDriver', 'absent', 'mainAndRepair', 'accident', 'daily'];
  const descriptionField = getElementById("paymentDescription");

  checkboxes.forEach((checkbox) => {
    getElementById(checkbox).checked = false;
  });

  descriptionField.value = "";
};

const shortBoundaryHandler = () => {
  if (selectedOptionPaymentType === "short" && getElementById("boundary").value !== "0") {
    const boundaryShort = parseFloat(getElementById("boundaryShort").value);
    const boundary = parseFloat(rate);
    if (!isNaN(boundaryShort) && !isNaN(boundary)) {
      setElementValue("boundary", boundary - boundaryShort);
    }
  }
};

const validatePaymentDescriptionHandler = () => {
  const myInputValue = getElementById("paymentDescription").value;

  setPaymentType();
  if (selectedOptionPaymentType === "fullBoundary" || selectedOptionPaymentType === "short") {
    return true;
  }

  if ((selectedOptionPaymentType !== "fullBoundary" || selectedOptionPaymentType !== "short") && myInputValue === "") {
    getElementById("errorDiv").style.display = "block";
    return false;
  }

  return true;
};

document.addEventListener("DOMContentLoaded", () => {
  getElementById("travelDate").valueAsDate = new Date();
  getElementById("paymentDate").valueAsDate = new Date();
  const searchParams = new URLSearchParams(window.location.search);
  const paramValue = searchParams.get("paymentId");
  if (paramValue === null) {
    setPaymentTypeHandler();
    getBoundaryRateHandler();
    clearOtherPaymentsCheckboxHandler();
  }
});
