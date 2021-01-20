<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <!-- head -->
        <%@include file="/RQB/include/head.jspf"  %>

        <script>
            var watch = null;
            
            $(document).ready(function () {
                $("#upt").on("click", function () {
                    var jsonObj = $('#myform').serializeObject();
                    var jsonStr = JSON.stringify(jsonObj);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/mvc/RQB/watch/" + watch_id,
                        type: "PUT",
                        contentType: "application/json; charset=utf-8",
                        data: jsonStr,
                        async: true,
                        cache: false,
                        processData: false,
                        success: function (resposeJsonObject) {
                            table_list1();
                        }
                    });
                });

                $("#myTable1").on("click", "tr td:nth-child(5)", function () {
                    var item_id = $(this).attr('item_id');
                    if (confirm("是否要刪除？" + item_id)) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/mvc/RQB/watch/" + watch_id + "/remove/" + item_id,
                            type: "DELETE",
                            async: true,
                            cache: false,
                            processData: false,
                            success: function (resposeJsonObject) {
                                table_list1();
                            }
                        });
                    }
                });
                
                $("#myTable2").on("click", "tr td:nth-child(5)", function () {
                    // 判斷該 item_id 是否已經加入 Watch ?
                    var item_id = $(this).attr('item_id');
                    var flag = true;
                    if (watch != null && watch.items != null) {
                        $.each(watch.items, function (i, it) {
                            if (it.id == item_id) {
                                alert(it.name + ' ' + it.symbol + ' 已加入！');
                                flag = false;
                                return;
                            }
                        });
                    }
                    if (flag && confirm("是否要增加？")) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/mvc/RQB/watch/" + watch_id + "/add/" + item_id,
                            type: "GET",
                            async: true,
                            cache: false,
                            processData: false,
                            success: function (resposeJsonObject) {
                                table_list1();
                            }
                        });
                    }
                });

                // 資料列表(Watch List)
                table_list1();
                // 資料列表(TStock List)
                table_list2();
            });
            
            // 1. 透過 watch_id 找到 watch
            // 2. 再透過 watch.items 取得item資料
            function table_list1() {
                $.get("${pageContext.request.contextPath}/mvc/RQB/watch/" + watch_id, function (data, status) {
                    console.log(JSON.stringify(data));
                    $("#myform").find("#id").val(data.id);
                    $("#myform").find("#name").val(data.name);
                    watch = data; // 設定 watch 變數資料
                    
                    $("#myTable1 tbody > tr").remove();
                    $.each(watch.items, function (i, it) {
                        var html = '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td item_id="{4}">{5}</td></tr>';
                        delbtn_html = '<button type="button" class="pure-button pure-button-primary">刪除</button>';
                        $('#myTable1').append(String.format(html,
                                it.id,
                                it.name,
                                it.symbol,
                                it.classify.name,
                                it.id,
                                delbtn_html
                                ));
                    });
                });
            }
            
            // 取得所有 item 列表資料
            function table_list2() {
                $.get("${pageContext.request.contextPath}/mvc/RQB/item/", function (datas, status) {
                    console.log(JSON.stringify(datas));
                    $("#myTable2 tbody > tr").remove();
                    $.each(datas, function (i, it) {
                        var html = '<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td item_id="{4}">{5}</td></tr>';
                        addbtn_html = '<button type="button" class="pure-button pure-button-primary">新增</button>';

                        $('#myTable2').append(String.format(html,
                                it.id,
                                it.name,
                                it.symbol,
                                it.classify.name,
                                it.id,
                                addbtn_html
                                ));
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
                    <h1>Watch</h1>
                    <h2>委託表, watch_id: ${sessionScope.watch_id}</h2>
                </div>
                <table>
                    <td valign="top">
                        <div class="content">
                            <form id="myform" class="pure-form">
                                <fieldset>
                                    <legend> <h2 class="content-subhead">資料維護</h2></legend>
                                    <input id="id" vslue="0"   name="id" placeholder="ID" readonly="true"/><p />
                                    <input id="name" name="name" placeholder="Watch 名稱"/><p />
                                    <button id="upt" type="button" class="pure-button pure-button-primary">修改</button>
                                </fieldset>
                            </form>
                        </div>
                    </td>  
                    <td valign="top">    
                        <div class="content">
                            <form class="pure-form">
                                <fieldset>
                                    <legend><h2 class="content-subhead">資料列表(Watch List)</h2></legend>
                                    <table id="myTable1" class="pure-table pure-table-bordered">
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>name</th>
                                                <th>symbol</th>
                                                <th>classify name</th>
                                                <th>刪除</th>
                                            </tr>
                                        </thead>

                                        <tbody>

                                        </tbody>
                                    </table> 
                                </fieldset>
                            </form>
                        </div>    
                    </td>
                    <td valign="top">    
                        <div class="content">
                            <form class="pure-form">
                                <fieldset>
                                    <legend><h2 class="content-subhead">資料列表(Item List)</h2></legend>
                                    <table id="myTable2" class="pure-table pure-table-bordered">
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>name</th>
                                                <th>symbol</th>
                                                <th>classify name</th>
                                                <th>增加</th>
                                            </tr>
                                        </thead>

                                        <tbody>

                                        </tbody>
                                    </table> 
                                </fieldset>
                            </form>
                        </div>    
                    </td>
                </table>


            </div>
        </div>
        <!-- Foot -->
        <%@include file="/RQB/include/foot.jspf"  %>   
    </body>
</html>
