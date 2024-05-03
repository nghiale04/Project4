
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListUrl" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Danh sách người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->


            </div>

            <div class="page-content">

                <div class="page-header">
                    <h1>
                        Danh sách người dùng
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <div class="widget-box ui-sortable-handle">
                    <div class="widget-header">
                        <h5 class="widget-title">Tìm kiếm</h5>

                        <div class="widget-toolbar">

                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>


                        </div>
                    </div>

                    <div class="widget-body" style="display: block;font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" >
                        <div class="widget-main">

                            <form:form id="listForm" modelAttribute="modelSearch" action="${customerListUrl}" method="GET">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label class="name">Tên khách hàng</label>
                                                    <%--                                                <input type="text" class="form-control" name="numberOfBassement" value="${modelSearch.numberOfBasement}">--%>
                                                <form:input path="fullname" class="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">Số điện thoại</label>
                                                    <%--                                                <input type="text" class="form-control" name="direction" value="${modelSearch.direction}">--%>
                                                <form:input path="phone" class="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">Email</label>
                                                    <%--                                                <input type="number" class="form-control" name="level" value="${modelSearch.level}">--%>
                                                <form:input path="email" class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-5">
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <label class="name">Nhân viên</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="">---Chọn nhân viên---</form:option>
                                                        <form:options items="${listStaffs}" />
                                                    </form:select>
                                                </security:authorize>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-xs-12">
                                        <div class="col-xs-6">
                                            <button class="btn btn-danger" id="btnSearchCustomer">Tiềm kiếm</button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div> <!-- /danh sach tim kiem -->
                    </div>

                    <div class="widget-title pull-right">
                        <a href="/admin/customer-edit">
                            <button class="btn btn-info" title="Thêm khách hàng">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                    <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                </svg>
                            </button>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                            <button class="btn btn-danger" title="Xóa khách hàng" id="btnDeleteCustomer">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-dash-fill" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M11 7.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5"/>
                                    <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                </svg>
                            </button>
                        </security:authorize>

                    </div>
                </div>
                <!-- Danh sách kết quả -->
                <div class="row" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin-top: 80px;">
                    <div class="col-xs-12">
                        <display:table name="customers" cellspacing="0" cellpadding="0"
                                       requestURI="${customerListUrl}" partialList="true" sort="external"
                                       size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="tableList" pagesize="${model.maxPageItems}" export="false"
                                       class="table table-striped table-bordered table-hover"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="${tableList.id}">
                                    <span class="lbl"></span>
                                </label>
                            </display:column>
                            <display:column headerClass="text-middle" property="fullname" title="Tên khách hàng"/>
                            <display:column headerClass="text-middle" property="phone" title="Di động"/>
                            <display:column headerClass="text-middle" property="email" title="Email"/>
                            <display:column headerClass="text-middle" property="demand" title="Nhu cầu"/>
                            <display:column headerClass="text-middle" property="createdBy" title="Người thêm"/>
                            <display:column headerClass="text-middle" property="createdDate" title="Ngày thêm"/>
                            <display:column headerClass="text-middle" property="status" title="Tình trạng"/>
                            <display:column title="Thao tác">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao Tòa Nhà" onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                                    </button>
                                </security:authorize>

                                <a class="btn btn-xs btn-info" title="Sửa tòa nhà" href="/admin/customer-edit-${tableList.id}">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </a>

                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>
                                </security:authorize>
                            </display:column>
                        </display:table>
                    </div><!-- /.span -->
                </div>

            </div><!-- /.page-content -->
        </div><!-- /.main-content -->

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->
</div>
<div class="modal fade" id="assignmentCustomerModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">DANH SÁCH NHÂN VIÊN</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">Chọn</th>
                        <th class="center">Tên Nhân Viên</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="bntassignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentCustomer(customerId){
        $('#assignmentCustomerModal').modal();
        loadStaffs(customerId);
        $('#customerId').val(customerId);
    }

    function loadStaffs(customerId){
        $.ajax({
            type: "GET",
            url: "${customerAPI}/"+ customerId+'/staffs',
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                var row='';
                $.each(response.data, function (index,item){
                    row += '<tr>';
                    row += '<td class ="text-center" ><input type="checkbox" value='+ item.staffId + ' id="checkbox_'+item.staffId+ '" class = "check-box-element"' + item.checked+'></td>';
                    row += '<td class ="text-center">'+item.fullName+'</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.info("success");

            },
            error : function (respond) {
                console.log("fail");
                window.location.href = "<c:url value="/admin/customer-list?message=erro"/>";
            }
        });
    }

    $('#bntassignmentCustomer').click(function(e){
        e.preventDefault();
        var data= {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(data['staffs'] != ''){
            assignment(data);
            window.alert("Giao thành công!")
        }
        console.log("OK");
    });

    function assignment(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}/" + 'assignment',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                console.info("success");
                window.location.href="<c:url value='/admin/customer-list?message=success'/>";
            },
            error : function (respond) {
                console.info("Giao không thành công!")
                window.location.href = "<c:url value="/admin/customer-list?message=success"/>";
            }
        });
    }
    $('#btnSearchCustomer').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    });

    function deleteCustomer(id){
        var customerId = [id];
        deleteCustomers(customerId);
    };

    $('#btnDeleteCustomer').click(function(e){
        e.preventDefault();
        var data= {};
        data['customerId'] = $('#customerId').val();
        var customerIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        deleteCustomers(customerIds);
    });

    function deleteCustomers(data){
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}/"+data,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Success");
            },
            error : function (respond) {
                console.log("fail");
                window.location.href = "<c:url value="/admin/customer-list?message=success"/> ";
            }
        });
    }
</script>
</body>
</html>



<%--<div class="row" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin-top: 80px;">--%>
<%--    <div class="col-xs-12">--%>
<%--        <table id="tableList" class="table table-striped table-bordered table-hover">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th class="center">--%>
<%--                    <label class="pos-rel">--%>
<%--                        <input type="checkbox" name="checkList" value="" class="ace">--%>
<%--                        <span class="lbl"></span>--%>
<%--                    </label>--%>
<%--                </th>--%>
<%--                <th>Tên khách hàng</th>--%>
<%--                <th>Di động</th>--%>
<%--                <th>Email</th>--%>
<%--                <th>Nhu cầu</th>--%>
<%--                <th>Người thêm</th>--%>
<%--                <th>Ngày thêm</th>--%>
<%--                <th>Người đổi</th>--%>
<%--                <th>Ngày đổi</th>--%>
<%--                <th>Tình trạng</th>--%>
<%--                <th>Thao tác</th>--%>

<%--            </tr>--%>
<%--            </thead>--%>

<%--            <tbody>--%>
<%--            <c:forEach var="item" items="${customers}">--%>
<%--            <tr>--%>
<%--                <td class="center">--%>
<%--                    <label class="pos-rel">--%>
<%--                        <input type="checkbox" class="ace" value="${item.id}">--%>
<%--                        <span class="lbl"></span>--%>
<%--                    </label>--%>
<%--                </td>--%>
<%--                <td>${item.fullname}</td>--%>
<%--                <td>${item.phone}</td>--%>
<%--                <td>${item.email}</td>--%>
<%--                <td>${item.demand}</td>--%>
<%--                <td>${item.createdBy}</td>--%>
<%--                <td>${item.createdDate}</td>--%>
<%--                <td>${item.modifiedBy}</td>--%>
<%--                <td>${item.modifiedDate}</td>--%>
<%--                <td>${item.status}</td>--%>
<%--                <td>--%>
<%--                    <div class="hidden-sm hidden-xs btn-group">--%>
<%--                        <button class="btn btn-xs btn-success" title="Giao Tòa Nhà" onclick="assignmentCustomer(${item.id})">--%>
<%--                            <i class="ace-icon glyphicon glyphicon-align-justify"></i>--%>
<%--                        </button>--%>

<%--                        <a class="btn btn-xs btn-info" title="Sửa tòa nhà" href="/admin/customer-edit-${item.id}">--%>
<%--                            <i class="ace-icon fa fa-pencil bigger-120"></i>--%>
<%--                        </a>--%>

<%--                        <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteCustomer(${item.id})">--%>
<%--                            <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            </c:forEach>--%>

<%--        </table>--%>
<%--    </div><!-- /.span -->--%>
<%--</div>--%>