<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>订单总金额</th>

                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.orderAmount}</td>

                </tr>

                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品id</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.getOrderDetailList() as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <#if orderDTO.getOrderStatusEnum().getMessage()=="新下单">
        <div class="col-md-12 column">
            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" class="btn btn-primary btn-default">完结订单</a>
            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" class="btn btn-danger btn-default">取消订单</a>
        </div>
        </#if>
    </div>
</div>
</body>
</html>