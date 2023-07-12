
//Set Variables
const paymentTypeInput = document.querySelector('input[name="paymentType"]:checked');
selectedOptionPaymentType = paymentTypeInput ? paymentTypeInput.value : 'fullBoundary';

// let selectedOptionPaymentType = document.querySelector('input[name="paymentType"]:checked').value;
selectElementVehicle = document.querySelector('#vehicle');
rate = selectElementVehicle.options[selectElementVehicle.selectedIndex].getAttribute('data-value');

const updateRate = () => {
  selectElementVehicle = document.querySelector('#vehicle');
  rate = selectElementVehicle.options[selectElementVehicle.selectedIndex].getAttribute('data-value');
}

const setPaymentTypeHandler = () => {

  updateRate();

  setPaymentType();

  document.getElementById("boundary").value = getBoundaryRate();

  clearPaymentDecriptionHandler();
};

const setPaymentType = () => {

  const paymentTypeInput = document.querySelector('input[name="paymentType"]:checked');
  selectedOptionPaymentType = paymentTypeInput ? paymentTypeInput.value : 'fullBoundary';


  if (selectedOptionPaymentType == "fullBoundary") {
    //disable radio button

    document.getElementById("otherPaymentsFund").disabled = false;
    document.getElementById("otherPaymentsLoan").disabled = false;
    document.getElementById("otherPaymentsContribution").disabled = false;

    document.getElementById("daily").disabled = true;
    document.getElementById("extraDriver").disabled = true;
    document.getElementById("noDriver").disabled = true;
    document.getElementById("absent").disabled = true;
    document.getElementById("mainAndRepair").disabled = true;
    document.getElementById("accident").disabled = true;

    document.getElementById("boundaryShort").readOnly = true;
    document.getElementById("boundary").readOnly = true;

    document.getElementById("boundaryShort").value = 0;

  } else if (selectedOptionPaymentType == "short") {
    document.getElementById("boundaryShort").value = 0;

    document.getElementById("otherPaymentsFund").disabled = true;
    document.getElementById("otherPaymentsLoan").disabled = true;
    document.getElementById("otherPaymentsContribution").disabled = true;

    document.getElementById("otherPaymentsFund").checked = false;
    document.getElementById("otherPaymentsLoan").checked = false;
    document.getElementById("otherPaymentsContribution").checked = false;

    document.getElementById("boundaryFund").value = 0;
    document.getElementById("boundaryLoan").value = 0;
    document.getElementById("contribution").value = 0;
    
    document.getElementById("daily").disabled = false;
    document.getElementById("extraDriver").disabled = true;
    document.getElementById("noDriver").disabled = true;
    document.getElementById("absent").disabled = true;
    document.getElementById("mainAndRepair").disabled = true;
    document.getElementById("accident").disabled = true;

    document.getElementById("boundary").readOnly = true;
    document.getElementById("boundaryShort").readOnly = false;

  } else if (selectedOptionPaymentType == "daily") {

    document.getElementById("otherPaymentsFund").disabled = false;
    document.getElementById("otherPaymentsLoan").disabled = false;
    document.getElementById("otherPaymentsContribution").disabled = false;

    document.getElementById("extraDriver").disabled = true;
    document.getElementById("noDriver").disabled = true;
    document.getElementById("absent").disabled = true;
    document.getElementById("mainAndRepair").disabled = true;
    document.getElementById("accident").disabled = true;

    document.getElementById("boundaryShort").readOnly = true;
    document.getElementById("boundary").readOnly = true;

    document.getElementById("boundaryShort").value = 0;
    

  } else if (selectedOptionPaymentType == "partialBoundary") {

    document.getElementById("otherPaymentsFund").disabled = false;
    document.getElementById("otherPaymentsLoan").disabled = false;
    document.getElementById("otherPaymentsContribution").disabled = false;

    document.getElementById("boundaryShort").readOnly = false;
    document.getElementById("boundary").readOnly = false;

    document.getElementById("daily").disabled = false;
    document.getElementById("extraDriver").disabled = false;
    document.getElementById("absent").disabled = false;
    document.getElementById("mainAndRepair").disabled = false;
    document.getElementById("accident").disabled = false;
    document.getElementById("noDriver").disabled = false;

    document.getElementById("boundaryShort").value = 0;
  } else if (selectedOptionPaymentType == "noBoundary") {
    //Payment Values
    document.getElementById("boundaryShort").value = 0;
    document.getElementById("boundary").value = 0;
    document.getElementById("boundaryFund").value = 0;
    document.getElementById("boundaryLoan").value = 0;
    document.getElementById("contribution").value = 0;
    //Payment State
    document.getElementById("boundary").readOnly = true;
    document.getElementById("boundaryShort").readOnly = true;
    //Payment Checkbox State
    document.getElementById("otherPaymentsFund").disabled = true;
    document.getElementById("otherPaymentsLoan").disabled = true;
    document.getElementById("otherPaymentsContribution").disabled = true;

    //disable all the radio button
    document.getElementById("extraDriver").disabled = true;
    document.getElementById("daily").disabled = true;
    document.getElementById("noDriver").disabled = false;
    document.getElementById("absent").disabled = false;
    document.getElementById("mainAndRepair").disabled = false;
    document.getElementById("accident").disabled = false;
  } 

}

const getBoundaryRate = () => {

  if (rate == null || selectedOptionPaymentType == "noBoundary") {
    return 0;
  } else {
    return rate;
  };

};

const getBoundaryRateHandler = () => {

  updateRate();
  document.getElementById("boundary").value = getBoundaryRate();
};


const adjustBoundaryRateHandler = () => {

  if (rate == null || selectedOption == "noBoundary") {
    document.getElementById("boundary").value = 0;
  } else {
    document.getElementById("boundary").value = rate;
  };

}

const adjustBoundaryRate = () => {

  selectedOptionPaymentType = document.querySelector(
    'input[name="paymentType"]:checked'
  ).value;

  document.getElementById("boundary").value = rate;

  let selectedRate = document.querySelector(
    'input[name="boundaryRate"]:checked'
  ).value;

  if ((selectedRate === "weekend" || selectedRate === "holiday" ) && rate !== null) {
    document.getElementById("boundary").value = rate - 100;
  } else if (selectedRate === "coding" && rate !== null) {
    document.getElementById("boundary").value = rate - 200;
  }

  if (rate === null || selectedOptionPaymentType === "noBoundary") {
    document.getElementById("boundary").value = 0;
  }

};

const otherPaymentsHandler = () => {

  let selectElementFund = document.querySelector('#otherPaymentsFund');
  let selectElementLoan = document.querySelector('#otherPaymentsLoan');
  let selectElementContribution = document.querySelector('#otherPaymentsContribution');
  let selectElementCarwash = document.querySelector('#otherPaymentsCarwash');

  if (selectElementFund.checked === true) {
    document.getElementById("boundaryFund").value = 50;
    document.getElementById("boundaryFund").readOnly = false;
    
  } else if (selectElementFund.checked === false) {
    document.getElementById("boundaryFund").value = 0;
    document.getElementById("boundaryFund").readOnly = true;
  }

  if (selectElementLoan.checked === true) {
    document.getElementById("boundaryLoan").value = 50;
    document.getElementById("boundaryLoan").readOnly = false;
  } else if (selectElementLoan.checked === false) {
    document.getElementById("boundaryLoan").value = 0;
    document.getElementById("boundaryLoan").readOnly = true;
  }

  if (selectElementContribution.checked === true) {
    document.getElementById("contribution").value = 40;
    document.getElementById("contribution").readOnly = false;

  } else if (selectElementContribution.checked === false) {
    document.getElementById("contribution").value = 0;
    document.getElementById("contribution").readOnly = true;
  } 

  if (selectElementCarwash.checked === true) {
    document.getElementById("carwash").value = 65;
    document.getElementById("carwash").readOnly = false;

  } else if (selectElementCarwash.checked === false) {
    document.getElementById("carwash").value = 0;
    document.getElementById("carwash").readOnly = true;
  }


};

const clearOtherPaymentsCheckboxHandler = () => {

  document.querySelector('#otherPaymentsFund').checked = false;
  document.querySelector('#otherPaymentsLoan').checked = false;
  document.querySelector('#otherPaymentsContribution').checked = false;
  document.querySelector('#otherPaymentsCarwash').checked = false;

  document.getElementById("contribution").value = 0;  
  document.getElementById("boundaryLoan").value = 0;
  document.getElementById("boundaryFund").value = 0;
  document.getElementById("carwash").value = 0;

  document.getElementById("contribution").readOnly = true;
  document.getElementById("boundaryLoan").readOnly = true;
  document.getElementById("boundaryFund").readOnly = true;
  document.getElementById("carwash").readOnly = true;

}

const appendPaymentDescriptionHandler = () => {

  let selectElementExtraDriver = document.querySelector('#extraDriver');
  let selectElementNoDriver = document.querySelector('#noDriver');
  let selectElementAbsent = document.querySelector('#absent');
  let selectElementMainAndRepair = document.querySelector('#mainAndRepair');
  let selectElementAccident = document.querySelector('#accident');
  let selectElementDaily = document.querySelector('#daily');

  document.getElementById("paymentDescription").value = "";

  if (selectElementExtraDriver.checked === true) {
    document.getElementById("paymentDescription").value += " " +selectElementExtraDriver.value;
  } 

  if (selectElementNoDriver.checked === true) {
    document.getElementById("paymentDescription").value += " " + selectElementNoDriver.value
  } 

  if (selectElementAbsent.checked === true) {
    document.getElementById("paymentDescription").value += " " + selectElementAbsent.value
  } 

  if (selectElementMainAndRepair.checked === true) {
    document.getElementById("paymentDescription").value += " " + selectElementMainAndRepair.value
  } 

  if (selectElementAccident.checked === true) {
    document.getElementById("paymentDescription").value += " " + selectElementAccident.value
  } 

  if (selectElementDaily.checked === true) {
    document.getElementById("paymentDescription").value += " " + selectElementDaily.value
  } 

}

const clearPaymentDecriptionHandler = () => {

  document.querySelector('#extraDriver').checked = false;
  document.querySelector('#noDriver').checked = false;
  document.querySelector('#absent').checked = false;
  document.querySelector('#mainAndRepair').checked = false;
  document.querySelector('#accident').checked = false;
  document.querySelector('#daily').checked = false;

  document.getElementById("paymentDescription").value = "";

}

const shortBoundaryHandler = () => {

  if (selectedOptionPaymentType == "short" && document.getElementById("boundary").value != 0) {
    console.log(document.getElementById("boundary").value);
    document.getElementById("boundary").value = rate - document.getElementById("boundaryShort").value;
  } 

}

const validatePaymentDescriptionHandler = () => {
  let myInputValue = document.getElementById("paymentDescription").value;

  setPaymentType();
  if (selectedOptionPaymentType === "fullBoundary" || selectedOptionPaymentType === "short") {
    return true;
  }

  if ((selectedOptionPaymentType != "fullBoundary" || selectedOptionPaymentType != "short") && myInputValue == "" ) {
    document.getElementById("errorDiv").style.display = "block";
    return false;
  }
}

$(document).ready(function () { 
  document.querySelector("#travelDate").valueAsDate = new Date();
  document.querySelector("#paymentDate").valueAsDate = new Date();
  const searchParams = new URLSearchParams(window.location.search);
  const paramValue = searchParams.get("paymentId");
  if (paramValue === null) {
    setPaymentTypeHandler();
    getBoundaryRateHandler();
    clearOtherPaymentsCheckboxHandler();
  }
  
});


