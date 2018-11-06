<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单列表页</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTOPage.getContent() as orderMaster>
                <tr>
                    <td>${orderMaster.orderId}</td>
                    <td>${orderMaster.buyerName}</td>
                    <td>${orderMaster.buyerPhone}</td>
                    <td>${orderMaster.buyerAddress}</td>
                    <td>${orderMaster.orderAmount}</td>
                    <td>${orderMaster.getOrderStatusEnum().getMessage()}</td>
                    <td>${orderMaster.getPayStatusEnum().getMessage()}</td>
                    <td>${orderMaster.createTime}</td>
                    <td>
                        <a href="/sell/seller/order/detail?orderId=${orderMaster.orderId}">详情</a>
                    </td>
                    <td>
                        <#if orderMaster.getOrderStatusEnum().getMessage() =="新下单">
                            <a href="/sell/seller/order/cancel?orderId=${orderMaster.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <ul class="pagination pull-right">
                <#if currentPage lte 1>
                <li class="disabled"><a href="javascript:;">上一页</a></li>
                <#else >
                <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=10">上一页</a></li>
                </#if>
                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentPage == index>
                <li class="disabled"><a href="javascript:;">${index}</a></li>
                    <#else>
                <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                    </#if>
                </#list>
                <#if currentPage gte orderDTOPage.getTotalPages()>
                <li class="disabled"><a href="javascript:;">下一页</a></li>
                <#else >
                <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=10">下一页</a></li>
                </#if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>