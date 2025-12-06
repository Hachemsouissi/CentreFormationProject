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
            margin-bottom: 30px;
        }
        .header h2 { color: #333; }
        .info-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }
        .info-box p {
            color: #666;
            line-height: 1.6;
        }
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
            color: #28a745;
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
        .btn-view {
            display: inline-block;
            padding: 8px 20px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background 0.3s;
        }
        .btn-view:hover { background: #218838; }
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
    <h1>🎓 Espace Apprenant</h1>
    <div>
        <a href="${pageContext.request.contextPath}/apprenant/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/apprenant/cours">Mes Cours</a>
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="header">
        <h2>📚 Mes Cours Disponibles</h2>
    </div>

    <div class="info-box">
        <p>
            <strong>Vous êtes inscrit à ${mesInscriptions.size()} formation(s).</strong>
            Retrouvez ci-dessous tous les cours mis à disposition par vos professeurs.
        </p>
    </div>

    <c:if test="${empty mesCours}">
        <div class="no-data">
            <h3>Aucun cours disponible</h3>
            <p>Vos professeurs n'ont pas encore ajouté de cours.</p>
        </div>
    </c:if>

    <div class="cours-grid">
        <c:forEach var="cours" items="${mesCours}">
            <div class="cours-card">
                <h3>📖 ${cours.titre}</h3>
                <div class="cours-info">
                    <p><strong>Formation:</strong> ${cours.formation.titre}</p>
                    <p><strong>Professeur:</strong> ${cours.professeur.prenom} ${cours.professeur.nom}</p>
                    <p><strong>Date:</strong> ${cours.dateCreation}</p>
                </div>
                <div class="cours-content">
                        ${cours.contenu}
                </div>
                <a href="${pageContext.request.contextPath}/apprenant/cours?action=view&id=${cours.id}"
                   class="btn-view">👁️ Voir le cours complet</a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>