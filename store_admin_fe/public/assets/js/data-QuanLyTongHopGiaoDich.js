"use strict";
// Class definition

var KTDatatableLocalSortDemo = function () {
  // Private functions

  // basic demo
  var demo = function () {
    var dataJSONArray = JSON.parse('[{"RecordID":1,"MaGiaoDich":"0374-5070","MaYeuCau":"0988477221","DonViTT":"Vietcombank","TenNhaCungCap":"1","MaKhachHang":"60310","HinhThucThuPhi":"Phí thu khách hàng","SoTienNoCuoc":"345.000","SoTienGiaoDich":"200.000","LoaiGiaoDich":"Truy vấn","ThoiGianGiaoDich":"2016-09-15 22:18:06","TrangThai":1,"Type":2,"Actions":null},\n' +
      '{"RecordID":2,"MaGiaoDich":"63868-257","MaYeuCau":"0988477221","DonViTT":"Agribank","TenNhaCungCap":"2","MaKhachHang":"26734","HinhThucThuPhi":"Phí thu khách hàng","SoTienNoCuoc":"400.000","SoTienGiaoDich":"300.000, Friesen and Grant","LoaiGiaoDich":"Truy vấn","ThoiGianGiaoDich":"2016-09-05 16:27:07","TrangThai":2,"Type":2,"Actions":null},\n' +
      '{"RecordID":3,"MaGiaoDich":"49230-191","MaYeuCau":"0988477221","DonViTT":"BIDV","TenNhaCungCap":"3","MaKhachHang":"8898","HinhThucThuPhi":"Phí thu khách hàng","SoTienNoCuoc":"1.000.000","SoTienGiaoDich":"1.000.000","LoaiGiaoDich":"Gạch nợ","ThoiGianGiaoDich":"2016-08-30 12:27:38","TrangThai":3,"Type":1,"Actions":null},\n' +
      '{"RecordID":4,"MaGiaoDich":"50865-056","MaYeuCau":"0988477221","DonViTT":"Vietcombank","TenNhaCungCap":"4","MaKhachHang":"1160","HinhThucThuPhi":"Phí thu khách hàng","SoTienNoCuoc":"2.000.000","SoTienGiaoDich":"1.900.000, Greenfelder and Gaylord","LoaiGiaoDich":"Gạch nợ","ThoiGianGiaoDich":"2016-12-27 22:25:10","TrangThai":1,"Type":3,"Actions":null}]');

    var datatable = $('#kt_datatable').KTDatatable({
      // datasource definition
      data: {
        type: 'local',
        source: dataJSONArray,
        pageSize: 10,
        serverPaging: false,
        serverFiltering: true,
        serverSorting: false,
        saveState: {
          cookie: true,
          webstorage: true,
        },
      },

      // layout definition
      layout: {
        scroll: false, // enable/disable datatable scroll both horizontal and vertical when needed.
        footer: false, // display/hide footer
      },

      // column sorting
      sortable: true,

      pagination: true,

      search: {
        input: $('#kt_datatable_search_query'),
        key: 'generalSearch'
      },

      // columns definition
      columns: [{
        field: 'RecordID',
        title: '#',
        sortable: 'asc',
        width: 30,
        type: 'number',
        selector: false,
        textAlign: 'center',
      }, {
        field: 'MaGiaoDich',
        title: 'Mã giao dịch',
        autoHide: false,
      }, {
        field: 'MaYeuCau',
        title: 'Mã yêu cầu/ sđt thanh toán',
      }, {
        field: 'DonViTT',
        title: 'Đơn vị TT',
      }, {
        field: 'TenNhaCungCap',
        title: 'Nhà cung cấp',
        autoHide: false,
        template: function (row) {
          var ncc = {
            1: {
              'title': 'Đại Học Ngoại Ngữ',
            },
            2: {
              'title': 'Đại Học Xây Dựng',
            },
            3: {
              'title': 'Đại Học Bách Khoa',
            },
            4: {
              'title': 'Đại Học KTQD',
            }
          };
          return ncc[row.TenNhaCungCap].title;
        },
      }, {
        field: 'MaKhachHang',
        title: 'Mã nộp tiền',
      }, {
        field: 'HinhThucThuPhi',
        title: 'hình thức thu phí',
      }, {
        field: 'SoTienNoCuoc',
        title: 'Số tiền nợ cước (VND)',
      }, {
        field: 'SoTienGiaoDich',
        title: 'Số tiền giao dịch (VND)',
      }, {
        field: 'LoaiGiaoDich',
        title: 'Loại giao dịch',
      }, {
        field: 'ThoiGianGiaoDich',
        title: 'Thời gian giao dịch',
        type: 'date',
        format: 'MM/DD/YYYY - h:m:i',
      }, {
        field: 'TrangThai',
        title: 'Trạng thái',
        autoHide: false,
        // callback function support for column rendering
        template: function (row) {
          var status = {
            1: {
              'title': 'Thành công',
              'class': 'label-light-success'
            },
            2: {
              'title': 'Thất bại',
              'class': ' label-light-danger'
            },
            3: {
              'title': 'Timeout',
              'class': ' label-light-warning'
            },
          };
          return '<span class="label font-weight-bold label-lg ' + status[row.TrangThai].class + ' label-inline label-bold">' + status[row.TrangThai].title + '</span>';
        },
      }, {
        field: 'Actions',
        title: 'Thao tác',
        sortable: false,
        width: 45,
        overflow: 'visible',
        autoHide: false,
        template: function (row) {
          return '\
                       <a href="chitietgiaodich.html?id='+ row.RecordID + '" class="btn btn-sm btn-clean" data-toggle="tooltip" data-theme="dark" title="Xem chi tiết"><img src="assets/media/icon/Info-circle.svg" /></a>\
';
        },
      }],
    });

    $('#kt_datatable_search_status').on('change', function () {
      var a = $(this).val().toLowerCase();
      datatable.search(a, 'Status');
    });
    $('#kt_datatable_search_ncc').on('change', function () {
      datatable.search($(this).val().toLowerCase(), 'TenNhaCungCap');
    });
    $('#kt_datatable_search_loaigiaodich').on('change', function () {
      datatable.search($(this).val().toLowerCase(), 'LoaiGiaoDich');
    });

    $('#kt_datatable_search_ncc, #kt_datatable_search_status, #kt_datatable_search_loaigiaodich').selectpicker();
  };

  return {
    // public functions
    init: function () {
      demo();
    },
  };
}();

jQuery(document).ready(function () {
  KTDatatableLocalSortDemo.init();
});
