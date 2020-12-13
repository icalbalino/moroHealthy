<%-- 
    Document   : stok
    Created on : Dec 13, 2020, 8:37:38 AM
    Author     : DB1407
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.ItemDao"%>
<%@page import="java.util.List"%>
<%@page import="model.Item"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
                integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous">
        </script>
        <script src="https://kit.fontawesome.com/3b79ccf7db.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" type="text/css"
              href="https://cdn.datatables.net/v/bs4/jq-3.3.1/dt-1.10.22/datatables.min.css" />
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>moroHealthy</title>
</head>
<body>
    <div class="d-flex vh-100">
        <div class="vh-100 side-menu-container d-flex flex-column justify-content space-between" id='side-menu'>
            <div class="menu-title"><img src="./img/jamu3.jpg" alt=""></div>
            <div class="list-group list-group-flush">
                <a href="dashboard.jsp" class="list-group-item list-group-item-action bg-light"><i class="fas fa-home col-2"></i>
                    <span class="col">Dashboard</span></a>
                <a href="transaksi.jsp" class="list-group-item list-group-item-action "><i class="fas fa-cube col-2"></i> <span class="col">Transaksi</span></a>
                <a href="stokbarang.jsp" class="list-group-item list-group-item-action"><i class="fas fa-cube col-2"></i> <span class="col">Stok Barang</span></a>
                <a href="history.jsp" class="list-group-item list-group-item-action bg-light"><i class="fas fa-history col-2"></i>
                    <span class="col">History</span></a>
                <a class="btn btn-success text-light" href="../logout.php">Logout</a>
            </div>
        </div>

        <div class="col container-fluid content">
            <div class="p-5" style="max-height: 80vh; overflow-y: scroll">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Id Barang</th>
                            <th>Nama Barang</th>
                            <th>Stok</th>
                            <th>Harga</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Item item : new ItemDao().getAll()) {
                        %>
                        <tr>
                            <td><%=item.getId()%></td>
                            <td><%=item.getNama()%></td>
                            <td><%=item.getStok()%></td>
                            <td><%=item.getHarga()%></td>
                            <td><a href=<%="stokbarang.jsp?update="+item.getId()%> type="button" class="btn btn-success text-light">UPDATE</a></td>
                            <!--<td><button type="button" class="btn btn-success text-light" data-toggle="modal" data-target="#myModal">UPDATE</button></td>-->
                        </tr>
                        <%}%>
                    </tbody>
                    </tr>
                </table>
                <div class="container">
                    
                </div>
            </div>
                    <button type="button" class="btn btn-info text-light" data-toggle="modal" data-target="#myModal">TAMBAH</button>
                    <div>
                        <div class="modal" id="myModal">
                            <form class="modal-dialog modal-xl" method="post" action="item">
                                <div class="modal-content">
                                    <div class="model-header">
                                        <h4 class="modal-title">TAMBAH ITEM</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>

                                    <div class="modal-body">
                                        <table class="table table-bordered">
                                            <tr>
                                                <th class="table-info" width="15%" nowrap>Nama item</th>
                                                <td><input class="form-control" type="text" name="nama" required></td>
                                            </tr>
                                            <tr>
                                                <th class="table-info">Stok</th>
                                                <td><input class="form-control" type="text" name="stok" required></td>
                                            </tr>
                                            <tr>
                                                <th class="table-info">Harga</th>
                                                <td><input class="form-control" type="text" name="harga" required></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="submit" class="btn btn-info" name="add" value="TAMBAH">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
        </div>
    </div>
                    
        <div class="modal" id="modal-update">
            <form class="modal-dialog modal-xl" method="post" action="item">
                <div class="modal-content">
                    <div class="model-header">
                        <h4 class="modal-title">TAMBAH ITEM</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <%
                        System.out.println("cek");
                        System.out.println(request.getParameter("update"));
                        if(request.getParameter("update")!=null){
                            Item item = new ItemDao().findById(Integer.parseInt(request.getParameter("update")+""));
                        %>
                    <div class="modal-body">
                        <input class="form-control" type="text" name="id" value="<%=item.getId()%>" hidden>
                        <table class="table table-bordered">
                            <tr>
                                <th class="table-info" width="15%" nowrap>Nama item</th>
                                <td><input class="form-control" type="text" name="nama" value="<%=item.getNama()%>" required></td>
                            </tr>
                            <tr>
                                <th class="table-info">Stok</th>
                                <td><input class="form-control" type="text" name="stok" value="<%=item.getStok()%>" required></td>
                            </tr>
                            <tr>
                                <th class="table-info">Harga</th>
                                <td><input class="form-control" type="text" name="harga"value="<%=item.getHarga()%>" required></td>
                            </tr>
                        </table>
                    </div>
                    <%}%>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-info" name="update" value="UPDATE">
                    </div>
                </div>
            </form>
        </div>
                    <%
          if(request.getParameter("update")!=null){
        %>
        <script>
            console.log("tes");
            $('#modal-update').modal("show");
        </script>
        <%}%>
</body>
</html>
