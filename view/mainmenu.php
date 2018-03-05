 <div class="container-fluid mainmenui">
     <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p>Purchase Order <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/po">2016</a></li>
            <li><a href="#">2017</a></li>
            <li><a href="#">2018</a></li>
          </ul>
      </li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p>Invoice <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/invoice">2016</a></li>
            <li><a href="#">2017</a></li>
            <li><a href="#">2018</a></li>
          </ul>
      </li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p>Quotation <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/quotation">2016</a></li>
            <li><a href="#">2017</a></li>
            <li><a href="#">2018</a></li>
          </ul>
      </li>
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p>Tax report <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/taxreport">รายงานภาษีซื้อ</a></li>            
            <li><a href="#">รายงานภาษีขาบ</a></li>
          </ul>
      </li>
      <!-- <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p>Product
           <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/service">Service</a></li>
            <li><a href="/product">Product</a></li>
          </ul>
      </li> -->
      <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Type <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/customer">Customer</a></li>
            <li><a href="/supplier">Supplier</a></li>
          </ul>
      </li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><p>{{\Auth::User()->name}} <span class="caret"></span></p></a>
          <ul class="dropdown-menu">
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
          </ul>
      </li>
    </ul>
  </div>