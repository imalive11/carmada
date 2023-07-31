function exportTableToExcel(reportType, startDate = "", endDate = "", driverName = "") {
  // Get the HTML table
  var report = document.querySelector('#report-for-export');

  // Clone the table to preserve the original
  var clonedReport = report.cloneNode(true);

  // Exclude hidden columns
  var hiddenColumns = clonedReport.querySelectorAll('th.hidden, td.hidden');
  hiddenColumns.forEach(function (column) {
    column.parentNode.removeChild(column);
  });

  // Parse the table data into a SheetJS worksheet object
  var ws = XLSX.utils.table_to_sheet(clonedReport, { raw: true });


  var range = XLSX.utils.decode_range(ws['!ref']);
  for (var R = range.s.r; R <= range.e.r; ++R) {
    for (var C = range.s.c; C <= range.e.c; ++C) {
      var cell_address = { c: C, r: R };
      if (!ws[cell_address]) {
        continue;
      }
    }
  }

  // Create a new Date object with the date string
  const dateString = $("#reportDate").text();;
  const date = new Date(dateString);

  // Get the month, day, and year from the date object
  const month = date.toLocaleString("default", { month: "short" });
  const day = date.getDate().toString().padStart(2, "0");
  const year = date.getFullYear().toString().substr(-2);

  // Format the date string as "mmm-dd-yy"
  const formattedDate = `${month}-${day}-${year}`;

  var file_name =''
  if (reportType == "daily") {
    file_name = 'Daily Report - ' + formattedDate + '.xlsx';
  } else if (reportType == "driver") {
    file_name = `${driverName}_${startDate}-${endDate}.xlsx`;
  }

  

  // Create a SheetJS workbook object and add the worksheet to it
  var wb = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

  // Save the workbook as an Excel file
  var wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
  saveAs(new Blob([wbout], { type: 'application/octet-stream' }), file_name);
}
// Sum up the values in the second column
var totalAmountReceived = 0;
var totalAmountBoundary = 0;
var totalAmountShort = 0;
var totalAmountFund = 0;
var totalAmountContribution = 0;
var totalAmountCarwash = 0;
var totalAmountLoanPayment = 0;
var totalAttendanceCount = 0;
var totalForRepair = 0;

$("td.amountReceived").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountReceived += amount;
})
$("td.amountBoundary").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountBoundary += amount;
})
$("td.amountShort").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountShort += amount;
})
$("td.amountFund").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountFund += amount;
})
$("td.amountContribution").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountContribution += amount;
})
$("td.amountLoanPayment").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountLoanPayment += amount;
})

$("td.amountCarwash").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalAmountCarwash += amount;
})

$("td.attendanceCount").each(function () {
  if ($(this).text() !== "NO_BOUNDARY") {
    totalAttendanceCount += 1;
  }
})

$("td.forRepair").each(function () {
  if ($(this).text().includes("maintenance")) {
    totalForRepair += 1;
  }
});


$("#totalAttendanceCount").text(totalAttendanceCount);

$("#totalRepair").text(totalForRepair);

$("#totalAmountReceived").text(totalAmountReceived.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountBoundary").text(totalAmountBoundary.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountShort").text(totalAmountShort.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountFund").text(totalAmountFund.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountContribution").text(totalAmountContribution.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountLoanPayment").text(totalAmountLoanPayment.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountCarwash").text(totalAmountCarwash.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));


// Performance Tab
$("#totalAmountReceived").text(totalAmountReceived.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#performance-total-boundary").text(totalAmountBoundary.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#performance-total-short").text(totalAmountShort.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#performance-total-fund").text(totalAmountFund.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#performance-total-contribution").text(totalAmountContribution.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#performance-total-loans").text(totalAmountLoanPayment.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#performance-total-attendance").text(totalAttendanceCount);


var sumByIncidentType = {
  CASH_ADVANCE: 0,
  VIOLATION: 0,
  DAMAGE: 0,
  CHARGE_BOUNDARY: 0
};

var totalIncidentAmount = 0;

// Iterate over each row in the table body
$("#incident-table tbody tr").each(function() {
  var incidentType = $(this).find(".incidentType").text().trim();
  var incidentAmount = parseFloat($(this).find(".incidentAmount").text().replace(/,/g, ""));
  sumByIncidentType[incidentType] += incidentAmount;
});

$("td.incidentAmount").each(function () {
  // Remove Comma before parsing
  var amount = parseInt($(this).text().replace(/,/g, '', 10));
  totalIncidentAmount += amount;
})

$("#totalIncidentAmount").text(totalIncidentAmount.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#incidents-charge-boundary").text(sumByIncidentType.CHARGE_BOUNDARY.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#incidents-cash-advance").text(sumByIncidentType.CASH_ADVANCE.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#incidents-violation").text(sumByIncidentType.VIOLATION.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#incidents-damages").text(sumByIncidentType.DAMAGE.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

var sumBoundaryByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var sumOtherPaymentsByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var sumCarwashLadyEllaByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var sumCarwashBoyByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};







// variables for late payments

var sumBoundaryLateByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var sumOtherPaymentsLateByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var sumCarwashLadyEllaLateByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var sumCarwashBoyLateByFranchise = {
  'Lady Ella': 0,
  'Grab Car': 0,
  'Arthura': 0
};

var totalAmountReceivedLate = 0;
var totalAmountBoundaryLate = 0;
var totalAmountShortLate = 0;
var totalAmountFundLate = 0;
var totalAmountContributionLate = 0;
var totalAmountLoanPaymentLate = 0;
var totalAmountCarwashLate = 0;

$("#report-for-export tbody tr").each(function() {
  var franchise = $(this).find(".franchise").text().trim();
  var amountBoundary = parseFloat($(this).find(".amountBoundary").text().replace(/,/g, ""));
  var amountFund = parseFloat($(this).find(".amountFund").text().replace(/,/g, ""));
  var amountContribution = parseFloat($(this).find(".amountContribution").text().replace(/,/g, ""));
  var amountLoanPayment = parseFloat($(this).find(".amountLoanPayment").text().replace(/,/g, ""));
  var amountCarwash = parseFloat($(this).find(".amountCarwash").text().replace(/,/g, ""));

  var amountOtherPayments = amountFund + amountContribution + amountLoanPayment;

  if (amountCarwash > 0 ) {
    var amountCarwashBoy = amountCarwash - 30;
    var amountCarwashLadyElla = amountCarwash - 35;
    sumCarwashLadyEllaByFranchise[franchise] += amountCarwashLadyElla;
    sumCarwashBoyByFranchise[franchise] += amountCarwashBoy;
  }

  var amountCarwashBoy = amountCarwash - 30;
  var amountCarwashLadyElla = amountCarwash - 35;

  if (!isNaN(amountBoundary) ){
  sumBoundaryByFranchise[franchise] += amountBoundary;
  sumOtherPaymentsByFranchise[franchise] += amountOtherPayments;

  }

  //Late boundary
  var amountReceivedLate = parseFloat($(this).find(".amountReceivedLate").text().replace(/,/g, ""));
  var amountBoundaryLate = parseFloat($(this).find(".amountBoundaryLate").text().replace(/,/g, ""));
  var amountShortLate = parseFloat($(this).find(".amountShortLate").text().replace(/,/g, ""));
  var amountFundLate = parseFloat($(this).find(".amountFundLate").text().replace(/,/g, ""));
  var amountContributionLate = parseFloat($(this).find(".amountContributionLate").text().replace(/,/g, ""));
  var amountLoanPaymentLate = parseFloat($(this).find(".amountLoanPaymentLate").text().replace(/,/g, ""));
  var amountCarwashLate = parseFloat($(this).find(".amountCarwashLate").text().replace(/,/g, ""));
  
  if (!isNaN(amountReceivedLate) ){
    totalAmountReceivedLate += amountReceivedLate;
    totalAmountBoundaryLate += amountBoundaryLate;
    totalAmountShortLate += amountShortLate;
    totalAmountFundLate += amountFundLate;
    totalAmountContributionLate += amountContributionLate;
    totalAmountLoanPaymentLate += amountLoanPaymentLate;
    totalAmountCarwashLate += amountCarwashLate;
  }
  
  
});

$("#totalAmountReceivedLate").text(totalAmountReceivedLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountBoundaryLate").text(totalAmountBoundaryLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountShortLate").text(totalAmountShortLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountFundLate").text(totalAmountFundLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountContributionLate").text(totalAmountContributionLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountLoanPaymentLate").text(totalAmountLoanPaymentLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));

$("#totalAmountCarwashLate").text(totalAmountCarwashLate.toLocaleString("en-US",
  {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  }
));



// Display the sum for 'Lady Ella' franchise in the HTML element with ID "incidents-damages"
//Report for Lady Ella
$("#totalBoundaryLadyElla").text(sumBoundaryByFranchise['Lady Ella'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalOtherPaymentsLadyElla").text(sumOtherPaymentsByFranchise['Lady Ella'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalCarwashEllaLadyElla").text(sumCarwashLadyEllaByFranchise['Lady Ella'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalCarwashBoyLadyElla").text(sumCarwashBoyByFranchise['Lady Ella'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

//Report for Arthura
$("#totalBoundaryArthura").text(sumBoundaryByFranchise['Arthura'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalOtherPaymentsArthura").text(sumOtherPaymentsByFranchise['Arthura'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalCarwashEllaArthura").text(sumCarwashLadyEllaByFranchise['Arthura'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalCarwashBoyArthura").text(sumCarwashBoyByFranchise['Arthura'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

//Report for Grab
$("#totalBoundaryGrab").text(sumBoundaryByFranchise['Grab Car'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalOtherPaymentsGrab").text(sumOtherPaymentsByFranchise['Grab Car'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalCarwashEllaGrab").text(sumCarwashLadyEllaByFranchise['Grab Car'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

$("#totalCarwashBoyGrab").text(sumCarwashBoyByFranchise['Grab Car'].toLocaleString("en-US", {
  minimumFractionDigits: 2,
  maximumFractionDigits: 2
}));

