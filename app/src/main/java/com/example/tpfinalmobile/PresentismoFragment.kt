package com.example.tpfinalmobile

import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PresentismoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView // Muestra lista de asistencias
    private lateinit var adapter: PresentismoAdapter // Enlaza datos de asistencia con viste de RecyclerView
    private var nombreMateria: String? = null
    private var porcentajePresentismo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nombreMateria = arguments?.getString(ARG_MATERIA) // Obtiene el nombre de la materia del Bundle de argumentos
        porcentajePresentismo = arguments?.getInt(ARG_PRESENTISMO) ?: 0 // Obtiene el porcentaje de presentismo; si no existe, usa 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_presentismo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asigna el nombre de la materia al TextView correspondiente
        view.findViewById<TextView>(R.id.tvMateria).text = nombreMateria

        // Muestra el porcentaje de presentismo en texto
        view.findViewById<TextView>(R.id.tvPorcentaje).text = "$porcentajePresentismo% de asistencia"

        // Configura la ProgressBar con el porcentaje recibido
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarHorizontal)
        progressBar.progress = porcentajePresentismo

        // Inicializa el RecyclerView y su layout manager (lista vertical)
        recyclerView = view.findViewById(R.id.recyclerAsistencias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val btnVolver = view.findViewById<ImageView>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            requireActivity().finish()
        }

        // Lista mock de asistencias (fecha y presente/ausente)
        val listaAsistencia = listOf(
            Asistencia("04/03", true),
            Asistencia("05/03", false),
            Asistencia("11/03", true),
            Asistencia("12/03", true),
            Asistencia("18/03", false),
            Asistencia("19/03", true),
            Asistencia("25/03", true),
            Asistencia("26/03", true)
        )

        // Crea el Adapter con la lista y lo asigna al RecyclerView
        adapter = PresentismoAdapter(listaAsistencia)
        recyclerView.adapter = adapter
    }

    companion object {
        // Clave para nombre de materia y porcentaje de presentismo en el Bundle de argumentos
        private const val ARG_MATERIA = "materia"
        private const val ARG_PRESENTISMO = "presentismo"

        // Crea una nueva instancia del fragment con el nombre de la materia y el porcentaje de presentismo
        fun newInstance(materia: String, presentismo: Int): PresentismoFragment {
            val fragment = PresentismoFragment()
            val args = Bundle()
            args.putString(ARG_MATERIA, materia)
            args.putInt(ARG_PRESENTISMO, presentismo)
            fragment.arguments = args
            return fragment
        }
    }
}