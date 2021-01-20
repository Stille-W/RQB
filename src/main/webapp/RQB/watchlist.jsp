<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <!-- head -->
        <!-- Watch List -->
        <%@include file="/RQB/include/head.jspf"  %>
        <script>
            $(document).ready(function () {
                watchList();
                // 下單
                $("#myTable").on("click", "tr td:nth-child(8)", function () {
                    var item_id = $(this).attr('item_id');
                    if (item_id == '') return;
                    if (confirm("是否要購買？")) {
                        amount = prompt("請輸入購買量");
                        if(amount == null) return;
                        if(amount % 1 != 0 || amount<=0) {
                            alert('請輸入大於0的整數');
                            return;
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/mvc/RQB/order/buy/" + item_id + "/" + amount,
                            type: "GET",
                            async: true, 
                            cache: false,  
                            processData: false, //To avoid making query String instead of JSON
                            success: function (resposeJsonObject) {
                               alert('成交回報: ' + resposeJsonObject);
                            }
                        });
                    }
                });
            });
            function watchList() {
                $.get("${pageContext.request.contextPath}/mvc/RQB/watch/" + watch_id, function (data, status) {
                    console.log(JSON.stringify(data));
                    
                    $("#myTable tbody > tr").remove();
                    $.each(data.items, function (i, it) {
                        var html = '<tr>' +
                                '<td align="center">{0}</td><td>{1}</td><td>{2}</td>' +
                                '<td align="right">{3}</td><td align="right">{4}</td>' +
                                '<td align="right">{5}</td><td>{6}</td>' +
                                '<td item_id="{7}">{8}</td>' +
                                '</tr>';
                        var item_id = "";
                        var buybtn_html = " ";
                        if (it.classify.tx) {
                            buybtn_html = '<button type="button" class="pure-button pure-button-primary">下單</button>';
                            item_id = it.id;
                        }
                        $('#myTable').append(String.format(html,
                                it.classify.name,
                                it.name,
                                it.symbol,
                                it.prePrice,
                                it.price,
                                numberFormat(it.quantity),
                                getYMDHMS(it.transactionDate),
                                item_id,
                                buybtn_html));
                    });
                });
            }
        </script>


    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/RQB/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/RQB/include/menu.jspf"  %>

            <div id="main">
                <div class="header">
                    <h1>Watch List</h1>
                    <h2 id="head2">Watch List</h2>
                </div>

                <table id="myTable" class="pure-table pure-table-bordered" width="100%">
                    <thead>
                        <tr>
                            <th>分類</th><th>名稱</th><th>代號</th>
                            <th>報價</th><th>實價</th><th>交易量</th>
                            <th>交易時間</th>
                            <th>購買</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <!-- Chart 繪圖容器 -->
                <div id="container" style="width:90%; height: 400px; margin:10px"></div>
            </div>
        </div>
        <!-- Foot -->
        <%@include file="/RQB/include/foot.jspf"  %>

    </body>
</html>