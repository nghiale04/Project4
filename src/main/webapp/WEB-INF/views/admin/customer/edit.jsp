
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Thêm hoặc sửa khách hàng</title>
</head>
<body>
<div class="main-content" id="main-container">
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

                <div class="page-header" >
                    <h1 style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
                        Sửa đổi hoặc thêm tòa nhà
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <form:form modelAttribute="customerEdit" id="listForm" method="GET" >
                    <div class="col-xs-12">
                        <form action="" class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-xs-3">Tên khách hàng</div>
                                <div class="col-xs-9">
                                    <form:input path="fullname" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Số điện thoại</div>
                                <div class="col-xs-9">
                                    <form:input path="phone" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Email</div>
                                <div class="col-xs-9">
                                    <form:input path="email" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Tên công ty</div>
                                <div class="col-xs-9">
                                    <form:input path="companyname" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Nhu cầu</div>
                                <div class="col-xs-9">
                                    <form:input path="demand" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tình trạng</label>
                                <form:select path="status" class="col-xs-9">
                                    <form:option value="">---Chọn tình trạng---</form:option>
                                    <form:options items="${statusType}"/>
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="bntAddOrUpdateCustomer">Cập nhập khách hàng</button>
                                        <button type="button" class="btn btn-primary" id="bntCancel">Hủy</button>
                                    </c:if>
                                    <c:if test="${empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="bntAddOrUpdateCustomer">Thêm khách hàng</button>
                                        <button type="button" class="btn btn-primary" id="bntCancel">Hủy</button>
                                    </c:if>
                                </div>

                            </div>
                            <form:hidden path="id" id="customerId"/>
                        </form>

                    </div>
                </form:form>
            </div><!-- /.page-content -->
        </div>
    <c:forEach var="item" items="${transactionType}">
        <div class="col-xs-12">
            <div class="col-xs-12">
                <h3 class="header smaller lighter blue">${item.value}</h3>
                <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}',${customerEdit.id})">
                    <i class="orange ace-icon fa fa-location-arrow"></i>Add
                </button>
            </div>
            <c:if test="${item.key == 'CSKH'}">
                <div class="col-xs-12">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Người đổi</th>
                                <th>Ngày đổi</th>
                                <th>Chi tiết giao dịch</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="itemm" items="${listCSKH}">
                            <tr>
                                <td>${itemm.createdDate}</td>
                                <td>${itemm.createdBy}</td>
                                <td>${itemm.modifiedBy}</td>
                                <td>${itemm.modifiedDate}</td>
                                <td>${itemm.note}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-success" data-toggle="tooltip" title="Sửa thông tin giao dịch" onclick="UpdataTransaction(${itemm.id},'${itemm.code}','${itemm.note}')">
                                            <i class="ace-icon fa fa-pencil bigger-120" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${item.key == 'DDX'}">
                <div class="col-xs-12">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Người đổi</th>
                            <th>Ngày đổi</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="itemm" items="${listDDX}">
                            <tr>
                                <td>${itemm.createdDate}</td>
                                <td>${itemm.createdBy}</td>
                                <td>${itemm.modifiedBy}</td>
                                <td>${itemm.modifiedDate}</td>
                                <td>${itemm.note}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-success" data-toggle="tooltip" title="Sửa thông tin giao dịch" onclick="UpdataTransaction(${itemm.id},'${itemm.code}','${itemm.note}')">
                                            <i class="ace-icon fa fa-pencil bigger-120" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </c:forEach>
    </div><!-- /.main-content -->




</div><!-- /.main-container -->
<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
                <div class="modal-body" >
                    <div class="form-group has-success">
                        <lable for="note" class="col-xs-12 col-sm-3 control-label no-padding-right" >Chi tiết giao dịch</lable>
                        <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="note" class="width-100">
                        </span>
                        </div>
                    </div>
                    <input type="hidden" name="customerId" id="customerId" value="">
                    <input type="hidden" name="code" id="code" value="">
                    <input type="hidden" name="id" id="id" value="">
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="bntAddOrUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script>
    function transactionType(code,customerId){
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
    }
    function UpdataTransaction(id, code,note){
        $('#transactionTypeModal').modal();
        $('#id').val(id);
        $('#code').val(code);
        $('#note').val(note);
    }
    $('#bntAddOrUpdateTransaction').click(function(e){
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['note'] = $('#note').val();
        if(data.note != ''){
            addTransaction(data);
            window.alert("Thành công");
            location.reload();
        }
    });
    function addTransaction(data){
        $.ajax({
            type: "post",
            url: '${customerAPI}/transaction',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                console.info("success!");
            },
            error : function (respond) {
                console.info("Fail!")
            }
        });
    }
    $('#bntAddOrUpdateCustomer').click(function (e){
       e.preventDefault();
       var data = {};
       var formData = $('#listForm').serializeArray();
       $.each(formData, function (i,v){
           data["" + v.name + ""] = v.value;
       });
        var customerId = data['id'];
        var fullName = data['fullname'];
        var phone = data['phone'];
        if(fullName != '' || phone != ''){
            window.location.href="<c:url value='/admin/customer-list?message=success'/>";
            updateAndAddCustomer(data);
            window.alert("Thêm thành công!");
        }else{
            window.location.href="<c:url value='/admin/customer-edit?name=required?phone=required'/>";
        }
    });
    function updateAndAddCustomer(data){
        $.ajax({
            type: "post",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                console.info("success!");

            },
            error : function (respond) {
                console.info("Fail!")
                console.log(respond);
            }
        });
    }

    $('#bntCancel').click(function (){
        window.location.href="/admin/customer-list";
    });

</script>
</body>
</html>
