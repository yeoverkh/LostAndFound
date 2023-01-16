<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Add new place</h2>

    <div class="form-group mt-2 p-2">
        <form method="post" action="/places">

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="address" placeholder="Address"/>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="latitude" placeholder="Latitude"/>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="longitude" placeholder="Longitude"/>
            </div>

            <div class="form-group">
                <input type="text" class="form-control col-sm-3" name="shortDescription"
                       placeholder="Short description"/>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <div class="form-group">
                <button type="submit" class="btn btn-success">Add</button>
            </div>
        </form>
    </div>
</@c.page>