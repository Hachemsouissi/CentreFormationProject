<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Cours</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
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
        .navbar h1 { font-size: 24px; }
        .navbar a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .navbar a:hover { background: rgba(255,255,255,0.2); }
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
        .header h2 { color: #333; }
        .btn-add {
            background: #667eea;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .btn-add:hover { background: #5568d3; }
        .cours-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
            gap: 20px;
        }
        .cours-card {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 25px;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .cours-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 20px rgba(0,0,0,0.15);
        }
        .cours-card h3 {
            color: #667eea;
            margin-bottom: 10px;
            font-size: 20px;
        }
        .cours-info {
            color: #666;
            font-size: 14px;
            margin-bottom: 15px;
        }
        .cours-info p {
            margin-bottom: 5px;
        }
        .cours-content {
            color: #333;
            line-height: 1.6;
            margin-bottom: 20px;
            max-height: 100px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .actions {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }
        .btn {
            padding: 8px 15px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer;
            border: none;
            transition: opacity 0.3s;
        }
        .btn:hover { opacity: 0.8; }
        .btn-view { background: #28a745; color: white; }
        .btn-edit { background: #ffc107; color: #333; }
        .btn-delete { background: #dc3545; color: white; }
        .no-data {
            text-align: center;
            padding: 60px 20px;
            color: #999;
            background: white;
            border-radius: 10px;
        }
        .no-data h3 { margin-bottom: 10px; color: #666; }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>🎓 Espace Professeur</h1>
    <div>
        <a href="${pageContext.request.contextPath}/professeur/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/professeur/cours">Mes Cours</a>
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="header">
        <h2>📚 Mes Cours</h2>
        <a href="${pageContext.request.contextPath}/professeur/cours?action=add" class="btn-add">
            ➕ Ajouter un cours
        </a>
    </div>

    <c:if test="${empty mesCours}">
        <div class="no-data">
            <h3>Aucun cours disponible</h3>
            <p>Commencez par ajouter votre premier cours !</p>
        </div>
    </c:if>

    <div class="cours-grid">
        <c:forEach var="cours" items="${mesCours}">
            <div class="cours-card">
                <h3>📖 ${cours.titre}</h3>
                <div class="cours-info">
                    <p><strong>Formation:</strong> ${cours.formation.titre}</p>
                    <p><strong>Date:</strong> ${cours.dateCreation}</p>
                </div>
                <div class="cours-content">
                        ${cours.contenu}
                </div>
                <div class="actions">
                    <a href="${pageContext.request.contextPath}/professeur/cours?action=view&id=${cours.id}"
                       class="btn btn-view">👁️ Voir</a>
                    <a href="${pageContext.request.contextPath}/professeur/cours?action=edit&id=${cours.id}"
                       class="btn btn-edit">✏️ Modifier</a>
                    <a href="${pageContext.request.contextPath}/professeur/cours?action=delete&id=${cours.id}"
                       class="btn btn-delete"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce cours ?')">
                        🗑️ Supprimer
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>