<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${cours.titre}</title>
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
            max-width: 900px;
            margin: 30px auto;
            padding: 0 20px;
        }
        .cours-header {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .cours-header h1 {
            color: #667eea;
            margin-bottom: 15px;
            font-size: 32px;
        }
        .cours-meta {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
            padding: 15px;
            background: #f8f9fa;
            border-radius: 5px;
        }
        .meta-item {
            display: flex;
            align-items: center;
            gap: 5px;
            color: #666;
        }
        .meta-item strong { color: #333; }
        .cours-content {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .cours-content h2 {
            color: #333;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #667eea;
        }
        .cours-content p {
            color: #333;
            line-height: 1.8;
            white-space: pre-wrap;
            word-wrap: break-word;
        }
        .back-link {
            display: inline-block;
            padding: 10px 20px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s;
        }
        .back-link:hover { background: #5568d3; }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>🎓 ${sessionScope.role == 'professeur' ? 'Espace Professeur' : 'Espace Apprenant'}</h1>
    <div>
        <c:choose>
            <c:when test="${sessionScope.role == 'professeur'}">
                <a href="${pageContext.request.contextPath}/professeur/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/professeur/cours">Mes Cours</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/apprenant/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/apprenant/cours">Mes Cours</a>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>
</nav>

<div class="container">
    <div class="cours-header">
        <h1>📖 ${cours.titre}</h1>
        <div class="cours-meta">
            <div class="meta-item">
                <strong>📚 Formation:</strong> ${cours.formation.titre}
            </div>
            <div class="meta-item">
                <strong>👨‍🏫 Professeur:</strong>
                ${cours.professeur.prenom} ${cours.professeur.nom}
            </div>
            <div class="meta-item">
                <strong>📅 Date:</strong> ${cours.dateCreation}
            </div>
        </div>
    </div>

    <div class="cours-content">
        <h2>Contenu du cours</h2>
        <p>${cours.contenu}</p>
    </div>

    <a href="${pageContext.request.contextPath}/${sessionScope.role}/cours" class="back-link">
        ← Retour à mes cours
    </a>
</div>
</body>
</html>