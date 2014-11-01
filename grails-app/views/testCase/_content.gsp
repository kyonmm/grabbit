<script>
  $(function(){
    var u = location.href.split("/")
    $('.nav-item').removeClass('active');
    $('.item-testCase').addClass('active');
  });
</script>
<div id="list" class="col-lg-8">
  <g:render template="list"/>
</div>
<div id="form" class="col-lg-4">
  <g:render template="form"/>
</div>
