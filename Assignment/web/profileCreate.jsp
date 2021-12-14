<%-- 
    Document   : profileCreate
    Created on : Dec 8, 2021, 8:30:08 PM
    Author     : tenhik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Profile</title>
        <link rel="icon" href="assets/favicon.ico" type="image/x-icon">
        <!--css file-->
        <link rel="stylesheet" href="assets/style.css">
        <!-- font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <!-- boostrap -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="header-block">
        </div>
        <div class="container-fluid">
            <div class="row"> 
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        <span class="font-weight-bold">${tempAcc.username}</span>
                        <span class="text-black-50">${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).email}</span>
                        <span>${tempAcc.email}</span>
                    </div>
                </div>
                <div class="col-md-5 border-right tab-content">
                    <div class="d-flex justify-content-between align-items-center mb-3 profile-title">
                        <p class="text-right">Điền thông tin cá nhân</p>
                    </div>
                    <div class="mt-3 profile-input">
                        <!--change info form-->
                        <form action="create-info" method="post">
                            <div class="col-md-12 mt-3">
                                <label class="labels">Họ và tên</label>
                                <input name="name" type="text" class="form-control text-uppercase" 
                                       placeholder="Nhập tên đầy đủ của bạn......."></div>
                            <div for="email" class="col-md-12 mt-3">
                                <label class="labels">Email</label>
                                <input name="email" type="email" class="form-control" 
                                       placeholder="Nhập email của bạn......." value="${tempAcc.email}">
                            </div>
                            <div class="col-md-12 mt-3">
                                <label class="labels">Số điện thoại</label>
                                <input name="phone" type="text" class="form-control"
                                       placeholder="Nhập số điện thoại của bạn.......">
                            </div>
                            <div class="col-md-12 mt-3">
                                <label class="labels">Địa chỉ</label>
                                <input name="address" type="text" class="form-control text-capitalize" 
                                       placeholder="Nhập địa chỉ hiện tại của bạn.......">
                            </div>
                            <div class="col-md-12 mt-3">
                                <label class="labels">Ngày sinh</label>
                                <input name="date" type="date" class="form-control" 
                                       placeholder="Nhập tháng ngày năm sinh.......">
                            </div>
                            <input type="hidden" name="username" value="${tempAcc.username}">
                            <input type="hidden" name="password" value="${tempAcc.password}">
                            <input type="hidden" name="info" value="true">
                            <div class="mt-5 text-center profile-save">
                                <button class="btn" type="submit">Lưu thông tin</button>
                            </div>
                        </form> 
                        <!--end change info form-->
                    </div>
                </div>
                <div class="col-md-4 edit-image-title">
                    <div class="d-flex justify-content-between"><p>Tải ảnh đại diện</p></div><br>
                    <form action="create-info" method="post" enctype="multipart/form-data">
                    <div class="p-3 py-5 edit-image">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        </div>
                        <input type="file" name="uploadfile" id="img" disabled style="display:none;"/>
                        <label for="img">Tải ảnh lên *</label>
                        <p style="font-size: 0.9rem;margin: 0.2rem">*Tính năng chưa sử dụng được</p>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="header-block">
        </div>
    </body>
</html>