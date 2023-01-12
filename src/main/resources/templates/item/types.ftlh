<#import "../parts/common.ftlh" as c>

<@c.page>
    <h2>Types</h2>

    <a class="btn btn-success mb-2" data-toggle="collapse" href="#addType" role="button" aria-expanded="false"
       aria-controls="addType">
        Add new type
    </a>

    <div class="collapse" id="addType">
        <div class="form-group mt-2 p-2">
            <form method="post" action="/types">

                <div class="form-group">
                    <input type="text" class="form-control col-sm-3"
                           name="name" placeholder="Name">
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}">

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Add</button>
                </div>
            </form>
        </div>
    </div>

    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <#if message == "danger">
                Type was deleted!
            <#elseif message == "info">
                Type was edited!
            <#else>
                Type was added!
            </#if>
        </div>
    </#if>

    <table class="table table-striped table-bordered table-hover" style="max-width: 600px">

        <thead class="thead-light">
        <tr>
            <th class="align-middle" scope="col" style="max-width: 0">Name</th>
            <th style="max-width: 0"></th>
            <th style="max-width: 0"></th>
        </tr>
        </thead>

        <tbody>
        <#list types as type>
            <tr>
                <td class="align-middle">${type.getName()}</td>
                <td style="max-width: 100px">
                    <form method="post" action="/types/edit/${type.getId()}">
                        <div class="input-group">
                            <input type="text" placeholder="${type.getName()}"
                                   class="form-control" name="name">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-success align-middle" type="submit" id="button-addon1">
                                Update
                            </button>
                        </div>
                    </form>
                </td>
                <td style="max-width: 40px">
                    <div>
                        <form method="post" action="/types/delete/${type.getId()}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="btn btn-danger" type="submit">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>

    </table>
</@c.page>