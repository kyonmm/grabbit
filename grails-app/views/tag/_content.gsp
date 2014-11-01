<div id="list" class="col-lg-8">
  <g:render template="list"/>
</div>
<div id="form" class="col-lg-4">
  <g:render template="form"/>
</div>
<script>
  $(function(){
    var u = location.href.split("/")
    $('.nav-item').removeClass('active');
    $('.item-tag').addClass('active');
  });
</script>
