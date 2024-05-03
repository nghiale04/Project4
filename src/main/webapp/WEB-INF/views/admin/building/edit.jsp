
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
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
                <form:form modelAttribute="buildingEdit" id="listForm" method="GET" >
                    <div class="col-xs-12">
                        <form action="" class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-xs-3">Tên tòa nhà</div>
                                <div class="col-xs-9">
                                    <form:input path="name" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-3">Quận</div>
                                <div class="col-xs-9">
                                    <form:select path="district" class="form-control">
                                        <form:option value="">---Chọn quận---</form:option>
                                        <form:options items="${districts}"/>
                                    </form:select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-3">Phường</div>
                                <div class="col-xs-9">
                                    <form:input path="ward" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Đường</div>
                                <div class="col-xs-9">
                                    <form:input path="street" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Kết cấu</div>
                                <div class="col-xs-9">
                                    <form:input path="structure" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Số tầng hầm</div>
                                <div class="col-xs-9">
                                    <form:input path="numberOfBasement" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Diện tích sàn</div>
                                <div class="col-xs-9">
                                    <form:input path="floorArea" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-3">Hướng </div>
                                <div class="col-xs-9">
                                    <form:input path="direction" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Hạng</div>
                                <div class="col-xs-9">
                                    <form:input path="level" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Diện tích thuê</div>
                                <div class="col-xs-9">
                                    <form:input path="rentArea" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Giá thuê</div>
                                <div class="col-xs-9">
                                    <form:input path="rentPrice" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Mô tả giá</div>
                                <div class="col-xs-9">
                                    <form:input path="priceDecription" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Phí dịch vụ</div>
                                <div class="col-xs-9">
                                    <form:input path="serviceFee" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Phí mô tô</div>
                                <div class="col-xs-9">
                                    <form:input path="motorbikefee" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Phí ngoài giờ</div>
                                <div class="col-xs-9">
                                    <form:input path="overtimefee" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Tiền điện</div>
                                <div class="col-xs-9">
                                    <form:input path="electricityfee" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Đặt cọc</div>
                                <div class="col-xs-9">
                                    <form:input path="deposit" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Thanh toán</div>
                                <div class="col-xs-9">
                                    <form:input path="payment" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Thời hạn thuê</div>
                                <div class="col-xs-9">
                                    <form:input path="renttime" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Thời gian trang trí</div>
                                <div class="col-xs-9">
                                    <form:input path="decorationtime" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Tên quản lý</div>
                                <div class="col-xs-9">
                                    <form:input path="managerName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">SĐT quản lý</div>
                                <div class="col-xs-9">
                                    <form:input path="managerPhone" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Phí mô giới</div>
                                <div class="col-xs-9">
                                    <form:input path="brokeragefee" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Loại tòa nhà</div>
                                <div class="col-xs-9">
                                    <form:checkboxes id="typeCode" items="${typeCodes}" path="typeCode"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-3">Ghi chú</div>
                                <div class="col-xs-9">
                                    <form:input path="note" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                                <div class="col-sm-9">
                                    <c:if test="${not empty buildingEdit.image}">
                                        <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                        <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                    </c:if>
                                    <c:if test="${empty buildingEdit.image}">
                                        <img src="/admin/image/defaul.jpg" id="viewImage" width="300px" height="300px">
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty buildingEdit.id}">
                                        <button type="button" class="btn btn-primary" id="bntAddOrUpdateBuilding">Cập nhập tòa nhà</button>
                                        <button type="button" class="btn btn-primary" id="bntCancel">Hủy tòa nhà</button>
                                    </c:if>
                                    <c:if test="${empty buildingEdit.id}">
                                        <button type="button" class="btn btn-primary" id="bntAddOrUpdateBuilding">Thêm tòa nhà</button>
                                        <button type="button" class="btn btn-primary" id="bntCancel">Hủy tòa nhà</button>
                                    </c:if>
                                </div>

                            </div>

                            <form:hidden path="id" id="buildingId"/>
                        </form>

                    </div>
                </form:form>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<script>
    var imageBase64 = '';
    var imageName = '';
    $('#bntAddOrUpdateBuilding').click(function(){
        var data = {};
        var typeCode =[];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i, v){
            if(v.name != 'typeCode'){
                data[""+ v.name +""] = v.value;
            }
            else{
                typeCode.push(v.value);
            }
        });
        $.each(formData, function (i, e) {
            if ('' !== e.value && null != e.value) {
                data['' + e.name + ''] = e.value;
            }

            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        var buildingId = data['id'];
        data['typeCode'] = typeCode;

        if(typeCode != ''){
            addOrupdate(data);
            window.location.href = "<c:url value="/admin/building-list?message=success"/>";
        }
        else{
            window.location.href = "<c:url value="/admin/building-edit?typeCode=require"/>";
        }
        $('#loading_image').show();
    });
    function addOrupdate(data){
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
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
        window.location.href="/admin/building-list";
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>