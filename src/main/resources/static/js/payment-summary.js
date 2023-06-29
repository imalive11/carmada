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
  if ($(this).text() !== "noBoundary") {
    totalAttendanceCount += 1;
  }
  
  
})

$("#totalAttendanceCount").text(totalAttendanceCount);

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