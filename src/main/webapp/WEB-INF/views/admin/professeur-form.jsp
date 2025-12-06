<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${professeur == null ? 'Ajouter' : 'Modifier'} un Professeur</title>
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
        }

        .navbar h1 {
            font-size: 24px;
        }

        .container {
            max-width: 600px;
            margin: 30px auto;
            padding: 0 20px;
        }

        .form-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .form-header {
            margin-bottom: 30px;
        }

        .form-header h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .form-header a {
            color: #667eea;
            text-decoration: none;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            color: #333;
            font-weight: 500;
            margin-bottom: 8px;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }

        .form-group input:focus {
            outline: none;
            border-color: #667eea;
        }

        .error-message {
            background: #fee;
            color: #c33;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }

        .btn-submit, .btn-cancel {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }

        .btn-submit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-cancel {
            background: #6c757d;
            color: white;
        }

        .btn-submit:hover {
            opacity: 0.9;
        }

        .btn-cancel:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>🎓 Gestion Formation</h1>
</nav>

<div class="container">
    <div class="form-card">
        <div class="form-header">
            <h2>${professeur == null ? '➕ Ajouter' : '✏️ Modifier'} un Professeur</h2>
            <a href="${pageContext.request.contextPath}/admin/professeurs">← Retour à la liste</a>
        </div>

        <c:if test="${error != null}">
            <div class="error-message">
                    ${error}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/professeurs" method="post">
            <input type="hidden" name="action" value="${professeur == null ? 'add' : 'edit'}">
            <c:if test="${professeur != null}">
                <input type="hidden" name="id" value="${professeur.id}">
            </c:if>

            <div class="form-group">
                <label for="nom">Nom *</label>
                <input type="text" id="nom" name="nom"
                       value="${professeur != null ? professeur.nom : ''}" required>
            </div>

            <div class="form-group">
                <label for="prenom">Prénom *</label>
                <input type="text" id="prenom" name="prenom"
                       value="${professeur != null ? professeur.prenom : ''}" required>
            </div>

            <div class="form-group">
                <label for="email">Email *</label>
                <input type="email" id="email" name="email"
                       value="${professeur != null ? professeur.email : ''}" required>
            </div>

            <div class="form-group">
                <label for="specialite">Spécialité</label>
                <input type="text" id="specialite" name="specialite"
                       value="${professeur != null ? professeur.specialite : ''}"
                       placeholder="Ex: Informatique, Mathématiques, etc.">
            </div>

            <div class="btn-group">
                <button type="submit" class="btn-submit">
                    ${professeur == null ? '➕ Ajouter' : '💾 Enregistrer'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/professeurs" class="btn-cancel">
                    ❌ Annuler
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>