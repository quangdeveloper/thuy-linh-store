$(document).ready(function () {
  //remove this section, do not use it, this is for deverlopment purpose only
  setTimeout(function () {
    KTUtil.ready(function () {
      KTLayoutScrolltop.init('kt_scrolltop');
      KTLayoutHeader.init('kt_header', 'kt_header_mobile');
      KTLayoutHeaderMenu.init('kt_header_menu', 'kt_header_menu_wrapper');
      KTLayoutAsideToggle.init('kt_aside_toggle')
      KTLayoutAside.init('kt_aside');
      KTLayoutAsideMenu.init('kt_aside_menu');
      initTable2();
    });
  }, 500);
  $.fn.dataTable.ext.errMode = 'none';
  //remove this section, do not use it, this is for deverlopment purpose only
  $('[data-tooltip="tooltip"]').tooltip({
    boundary: 'window'
  })
  //data table
  var initTable2 = function () {
    var table = $('#kt_datatable2');

    // begin second table
    table.DataTable({
      scrollX: true,
      scrollCollapse: true,
      "searching": false,
      "dom": '<"top"i>rt<"bottom"flp><"clear">',
      columnDefs: [
        { orderable: true, targets: -1 }
      ],
      "initComplete": function (settings, json) {
        $('.dataTables_scrollBody').floatingScroll();
        $('.dataTable-preload').removeClass('dataTable-preload')
      },
      "language": {
        "decimal":        "",
    "emptyTable":     "Dữ liệu trống",
    "info": "Tổng số <b>_MAX_</b> bản ghi ",
    "infoEmpty":      "Showing 0 to 0 of 0 bản ghi",
    "infoFiltered":   "(filtered from _MAX_ total bản ghi)",
    "infoPostFix":    "",
    "thousands":      ",",
    "lengthMenu":     "Hiển thị _MENU_ bản ghi",
    "loadingRecords": "Loading...",
    "processing":     "Processing...",
    "search":         "Tìm kiếm:",
    "zeroRecords":    "Không tìm thấy",
  "aria": {
      "sortAscending":  ": activate to sort column ascending",
      "sortDescending": ": activate to sort column descending"
  }

    },

    });
  };
  //end data table
  initTable2();

  // tablescroll
  $('.dataTables_scrollBody').scroll(function (e) {
    var _this = this;
    if (_this.scrollWidth === (_this.scrollLeft + _this.clientWidth)) {
      $(_this).addClass('right-none');
      $('.dataTables_scrollHeadInner').addClass('right-none');
    }
    else {
      $(_this).removeClass('right-none');
      $('.dataTables_scrollHeadInner').removeClass('right-none');
    };
  }).scroll();
  // end tablescroll
  //datepicker basic
$('.b-datepicker').datepicker({
  autoclose: true,disableTouchKeyboard: true,
  Readonly: true,
  todayHighlight: true,
   format: "dd/mm/yyyy",
   language: 'vi'
}).attr("readonly", "readonly");
// end datepicker basic
  $(window).on('scroll', function () {
    if ($(this).scrollTop() > $('#kt_header').height()) {
      $('#kt_subheader').addClass('stucked')
    }
    else {
      $('#kt_subheader').removeClass('stucked')
    }
  });
});

"use strict";