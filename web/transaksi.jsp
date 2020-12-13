<%-- 
    Document   : dashboard
    Created on : Dec 12, 2020, 8:11:16 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="dao.ItemDao"%>
<%@page import="java.util.List"%>
<%@page import="model.Item"%>
<%@page import="model.Cart"%>

<html lang='en'>

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
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/jq-3.3.1/dt-1.10.22/datatables.min.js">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
    <script type="text/javascript"
        src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/js/dataTables.checkboxes.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.bootstrap4.min.css">

        <link rel='stylesheet' href='./css/style.css'>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <title>moroHealthy</title>
    </head>

    <body>

        <div class='d-flex' id='wrapper'>
            <div class='vh-100 side-menu-container d-flex flex-column justify-content space-between' id='side-menu'>
                <div class='menu-title'><img src='./img/jamu3.jpg' alt=''></div>
                <div class='list-group list-group-flush'>
                    <a href='dashboard.jsp' class='list-group-item list-group-item-action'> <i class='fas fa-home col-2'></i> <span class='col'>Dashboard</span></a>
                    <a href='transaksi.jsp' class='list-group-item list-group-item-action list-group-item-success'> <i class='fas fa-money-check col-2'></i> <span class='col'>Transaksi</span></a>
                    <a href='stokbarang.jsp' class='list-group-item list-group-item-action'> <i class='fas fa-cube col-2'></i> <span class='col'>Stok Barang</span></a>
                    <a href='history.jsp' class='list-group-item list-group-item-action'> <i class='fas fa-history col-2'></i> <span class='col'>History</span></a>
                    <a class='btn btn-success text-light' href=''>Logout</a>
                </div>    
            </div>

            <div class='col container-fluid content'>
                <div class="d-flex justify-content-center align-items-center vh-100 mh-100">
                <!-- <button class="btn btn-success">Transaksi Baru</button> -->
                <div class="card border-0 card-shadow" style="width: 32em">
                    <div class="h3 p-3">Keranjang</div>
                    <div class="list-group list-group-flush" style="max-height: 75vh; overflow-y: scroll" id="cart">
                        <%
                            if(session.getAttribute("sessionCart")==null){
                        %>
                            <center>Keranjang masih kosong</center>

                        <%}else{
                            for(Cart cart:(List<Cart>)session.getAttribute("sessionCart")){
                        %>
                        <div class="list-group-item d-flex align-items-center">
                            <div class="col"><%=cart.getItem().getNama()%></div>
                            <div class="col"><%=cart.getItem().getHarga()%></div>
                            <div class="col"><%=cart.getQty()%></div>
                        </div>
                        <%}}%>
<!--                            foreach($carts as $cart){
                              $subtotal = $cart['harga']*$cart['qty'];
                              $total+=$subtotal;
                              echo '<div class="list-group-item d-flex align-items-center">
                                        <div class="col">'.$cart["nama"].'</div>
                                        <div class="col">'.$cart["qty"].'</div>
                                        <div class="col">'.$cart["harga"].'</div>
                                        <a href="transaksi.php?delete&id='.$cart['id'].'" style="background: none; border: none"><i
                                                class="fas fa-trash text-danger"></i></a>
                                    </div>';
                            }
                        ?>-->
                    </div>
                    <div class="card-footer" style="margin-bottom: 20px">
                        <p>Total: <?php echo $total;?></p>
                        <div class="float-right">
<!--                            <?php
                                if(count($carts) > 0){
                                    echo '<button class="btn btn-danger" onclick="willDeleteCart()">Hapus Keranjang</button>';
                                }
                            ?>-->

                            <button class="btn btn-success" data-toggle="modal" data-target="#modal-item-list">Tambah Item</button>
                        </div>
                    </div>

                    <div class="p-4" style="text-align:right;">
                        <%
                            if(session.getAttribute("sessionCart")!=null){
                        %>
                        <a class="btn btn-primary" href="transaksi?checkout">Simpan Transaksi</a>
                        <%}%>
<!--                        <?php
                        if(count($carts)>0)
                            echo '<a class="btn btn-primary" href="transaksi/checkout.php">Pembayaran</a>'
                        ?>-->
                    </div>
                </div>
            </div>
            </div>
        </div>
        <form id="modal-item-list" class="modal" tabindex="-1" role="dialog" action="transaksi" method="POST">
        <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Tambah Item</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body vh-50 mh-50">
                    <table class="table" id="item-table" data-page-length='5'>
                        <thead>
                            <th>Id</th>
                            <th>Nama</th>
                            <th>Harga Satuan</th>
                            <th>Kuantitas</th>
                        </thead>
                        <tbody>
                            <%
                                List<Item> items = new ItemDao().getAll();
                                for(int i=0; i < items.size(); i++){
                            %>
                                <tr>
                                    <td><%= items.get(i).getId()%></td>
                                    <td><%= items.get(i).getNama()%></td>
                                    <td><%= items.get(i).getHarga()%></td>
                                    <td><input type='number' class='form-controll' name="qty[<%=items.get(i).getId()%>]"></td>
                                </tr>
                            <%
                            }    
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <input class="btn btn-success" type="Submit" value="Tambahkan" name="addToCart">
                </div>
            </div>
        </div>
    </form>
<script>
        $(document).ready(function () {
            $('#item-table').DataTable({
                "bInfo": false,
                "lengthChange": false,
            });
        });

        var carts = [{
            "name": "brokoli",
            "qty": 10,
            "price": 10000
        }]

        function addItemToCart(e, data) {
            console.log("add item", data)
            array_push(carts, data)
            renderList()
        }

        function renderList() {
            var html = "";
            for (var i = 0; i < carts.length; i++) {
                console.log(i)
                var cart = carts[i]
                // console.log(carts)
                html += '<div class="list-group-item d-flex align-items-center">' +
                    '<div class="col">' + cart.name + '</div>' +
                    '<div class="col">' + cart.qty + '</div>' +
                    '<div class="col">Rp' + (cart.qty * cart.price) + '</div>' +
                    '<button style="background: none; border: none"><i class="fas fa-trash text-danger"></i></button>' +
                    '</div>'
            }
            console.log(html)
            $('#cart').append(html)
        }

        function willDeleteCart() {
            Swal.fire({
                title: 'Hapus Keranjang',
                text: "Anda yakin ingin menhapus keranjang?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Hapus',
                cancelButtonText: 'Batal'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "transaksi.php?delete"
                }
            })
        }
    </script>
    </body>

</html>
