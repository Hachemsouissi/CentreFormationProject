<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Formations</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }

        .navbar {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h1 {
            font-size: 24px;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }

        .navbar a:hover {
            background: rgba(255,255,255,0.2);
        }

        .container {
            max-width: 1400px;
            margin: 30px auto;
            padding: 0 20px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .header h2 {
            color: #333;
        }

        .btn-add {
            background: #667eea;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }

        .btn-add:hover {
            background: #5568d3;
        }

        .table-container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 900px;
        }

        thead {
            background: #f8f9fa;
        }

        th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
            color: #555;
            border-bottom: 2px solid #e0e0e0;
        }

        td {
            padding: 15px;
            border-bottom: 1px solid #f0f0f0;
        }

        tbody tr:hover {
            background: #f8f9fa;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .btn-edit, .btn-delete {
            padding: 6px 12px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer;
            border: none;
        }

        .btn-edit {
            background: #ffc107;
            color: #333;
        }

        .btn-delete {
            background: #dc3545;
            color: white;
        }

        .btn-edit:hover {
            background: #e0a800;
        }

        .btn-delete:hover {
            background: #c82333;
        }

        .no-data {
            text-align: center;
            padding: 40px;
            color: #999;
        }

        .description {
            max-width: 300px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>🎓 Gestion Formation</h1>
    <div>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/apprenants">Apprenants</a>
        <a href="${pageContext.request.contextPath}/admin/professeurs">Professeurs</a>
        <a href="${pageContext.request.contextPath}/admin/formations">Formations</a>
        <a href="${pageContext.request.contextPath}/admin/inscriptions">Inscriptions</a>
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="header">
        <h2>Liste des Formations</h2>
        <a href="${pageContext.request.contextPath}/admin/formations?action=add" class="btn-add">
            ➕ Ajouter une formation
        </a>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Durée (h)</th>
                <th>Capacité</th>
                <th>Professeur</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty formations}">
                <tr>
                    <td colspan="7" class="no-data">Aucune formation trouvée</td>
                </tr>
            </c:if>

            <c:forEach var="formation" items="${formations}">
                <tr>
                    <td>${formation.id}</td>
                    <td><strong>${formation.titre}</strong></td>
                    <td>${formation.duree}h</td>
                    <td>${formation.capacite} places</td>
                    <td>
                            ${formation.professeur != null ?
                                    formation.professeur.nom.concat(' ').concat(formation.professeur.prenom) :
                                    '⚠️ Non assigné'}
                    </td>
                    <td>
                        <div class="description" title="${formation.description}">
                                ${formation.description != null ? formation.description : 'Aucune description'}
                        </div>
                    </td>
                    <td>
                        <div class="actions">
                            <a href="${pageContext.request.contextPath}/admin/formations?action=edit&id=${formation.id}"
                               class="btn-edit">✏️ Modifier</a>
                            <a href="${pageContext.request.contextPath}/admin/formations?action=delete&id=${formation.id}"
                               class="btn-delete"
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette formation ?')">
                                🗑️ Supprimer
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>